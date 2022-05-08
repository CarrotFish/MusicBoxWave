package com.carrotfishstudio.wavemaker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wave{
    public File wavFile;
    public DataInputStream input;
    public DataOutputStream output;
    public List<Byte> data;

    //以下是数据区
    //RIFF头
    public byte[] RIFF = new byte[]{
        (byte)'R', (byte)'I', (byte)'F', (byte)'F'
    };//大端
    public byte[] length = new byte[]{
        0, 0, 0, 0
    };
    public int length_int;
    public byte[] WAVE = new byte[]{
        (byte)'W', (byte)'A', (byte)'V', (byte)'E'
    };//大端
    //fmt(format) chunk
    public byte[] fmt = new byte[]{
        (byte)'f', (byte)'m', (byte)'t', (byte)' '
    };      //'fmt '大端
    public byte[] fmt_size = new byte[]{
        16, 0, 0, 0
    };     //16
    public byte[] audioformat = new byte[2];//音频格式 1
    public byte[] numchannels = new byte[2];//声道数 2
    public byte[] samplerate = new byte[4];//采样率
    public byte[] byterate = new byte[4];//每秒数据字节数 SampleRate * NumChannels * BitsPerSample / 8
    public byte[] blockalign = new byte[2];//数据块对齐 NumChannels * BitsPerSample / 8
    public byte[] bitspersample = new byte[2];//采样位数 8：8bit，16：16bit，32：32bit
    public int audioformat_int,
               numchannels_int,
               samplerate_int,
               byterate_int,
               blockalign_int,
               bitspersample_int;
    //data chunk
    public byte[] id = new byte[]{
        (byte)'d', (byte)'a', (byte)'t', (byte)'a'
    };//'data'大端
    public byte[] data_size = new byte[4];//ByteRate * seconds
    public int data_size_int;
    public List<Byte> data_content;//数据
    public List<DataChunk161> chunks;//数据块

    //读取的打开方式
    public Wave(File file){
        wavFile = file;
        data = new ArrayList<Byte>();
        openInput();
        boolean on_while = true;
        while(on_while){
            try {
                data.add(input.readByte());
            }
            catch (EOFException e){
                on_while = false;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeInput();
    }
    //写入的打开方式  文件 格式 声道数 采样率 采样深度
    public Wave(File file, int format, int channels, int rate, int bit){
        wavFile = file;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //拷贝设置
        audioformat_int = format;
        numchannels_int = channels;
        samplerate_int = rate;
        bitspersample_int = bit;
        audioformat[0] = (byte)format;
        audioformat[1] = (byte)(format>>8);
        numchannels[0] = (byte)channels;
        numchannels[1] = (byte)(channels>>8);
        samplerate[0] = (byte)rate;
        samplerate[1] = (byte)(rate>>8);
        samplerate[2] = (byte)(rate>>16);
        samplerate[3] = (byte)(rate>>24);
        bitspersample[0] = (byte)bit;
        bitspersample[1] = (byte)(bit>>8);
        //计算其他设置
        byterate_int = samplerate_int*numchannels_int*bitspersample_int/8;
        blockalign_int = numchannels_int*bitspersample_int/8;
        //保存其他设置
        byterate = int2bytes(byterate_int, 4);
        blockalign = int2bytes(blockalign_int, 2);
        //初始化列表
        data = new ArrayList<>();
        data_content = new ArrayList<>();
        chunks = new ArrayList<>();
    }

    public void openInput(){
        try {
            input = new DataInputStream(new FileInputStream(wavFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void openOutput(){
        try {
            output = new DataOutputStream(new FileOutputStream(wavFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void closeInput(){
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeOutput(){
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写入文件
    public void writeFile(){
        //写入data_content
        writeDataContent();
        //换算
        changeInt2Bytes();
        //写入文件
        openOutput();
        addData(RIFF);
        addData(length);
        addData(WAVE);
        addData(fmt);
        addData(fmt_size);
        addData(audioformat);
        addData(numchannels);
        addData(samplerate);
        addData(byterate);
        addData(blockalign);
        addData(bitspersample);
        addData(id);
        addData(data_size);
        addData(al2bytes(data_content));
        try {
            output.write(al2bytes(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeOutput();
    }

    //数字转小端
    public static byte[] int2bytes(int n, int count){
        byte[] result = new byte[count];
        for(int i=0;i<count;i++){
            result[i] = (byte)(n>>(i*8));
        }
        return result;
    }
    //ArrayList转bytes
    public static byte[] al2bytes(List<Byte> n){
        if(n.size()==0) return new byte[0];
        Byte[] resultByte = new Byte[n.size()];
        byte[] result = new byte[n.size()];
        n.toArray(resultByte);
        for(int i=0; i<n.size(); i++){
            result[i] = resultByte[i];
        }
        return result;
    }

    //添加data
    private void addData(byte[] nd){
        for(byte i : nd){
            data.add(i);
        }
    }
    private void addDataContent(byte[] nd){
        for(byte i : nd){
            data_content.add(i);
        }
    }

    //换算
    private void changeInt2Bytes(){
        length_int = 4+4+4+2+2+2+4+4+2+2+4+4+data_content.size();
        data_size_int = data_content.size();
        length = int2bytes(length_int, 4);
        audioformat = int2bytes(audioformat_int, 2);
        numchannels = int2bytes(numchannels_int, 2);
        samplerate = int2bytes(samplerate_int, 4);
        bitspersample = int2bytes(bitspersample_int, 2);
        //计算其他设置
        byterate_int = samplerate_int*numchannels_int*bitspersample_int/8;
        blockalign_int = numchannels_int*bitspersample_int/8;
        //保存其他设置
        byterate = int2bytes(byterate_int, 4);
        blockalign = int2bytes(blockalign_int, 2);
        data_size = int2bytes(data_size_int, 4);
    }
    //写入data_content
    private void writeDataContent(){
        for(int i=0;i<chunks.size();i++){
            addDataContent(chunks.get(i).data);
        }
    }
}
