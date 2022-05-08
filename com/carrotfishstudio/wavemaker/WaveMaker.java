package com.carrotfishstudio.wavemaker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/imxiangzi/article/details/80265978


public class WaveMaker {

    //以下是常量区
    //音高数据来自-> https://pages.mtu.edu/~suits/notefreqs.html
    public final static double C5=523.25,
        C4=261.63,D4=293.66,E4=329.63,F4=349.23,G4=392.00,A4=440.00,B4=493.88,
                  D5=587.33,E5=659.25,F5=698.46,G5=783.99,A5=880.00,B5=987.33,
        C6=1046.50,D6=1174.66,E6=1318.51,F6=1396.91,G6=1567.98,A6=1760.00,B6=1975.53,
        C7=2093.00,D7=2349.32,E7=2637.02,F7=2793.83,G7=3135.96,A7=3520.00,B7=3951.07;
    public final static double
        C4r=277.18,D4r=311.13,F4r=369.99,G4r=415.30,A4r=466.16,
        C5r=554.37,D5r=622.25,F5r=739.99,G5r=830.61,A5r=932.33,
        C6r=1108.73,D6r=1244.51,F6r=1479.98,G6r=1661.22,A6r=1864.66,
        C7r=2217.46,D7r=2489.02,F7r=2959.96,G7r=3322.44,A7r=3729.31;
    public final static double NOPE = 0;
    //默认数据
    public final static double DEFALT_AE=10, DEFALT_K0=2, DEFALT_K1=10, DEFALT_K2=90, DEFALT_K3=150;

    Wave wave;
    int A = 5000;
    public WaveMaker(File file, int rate){
        wave = new Wave(file, 1, 1, rate, 16);
        for(int t=0; t<rate*3;t++){
            wave.chunks.add(new DataChunk161((int)(A*Math.cos(2*Math.PI*432*t/rate)+A)));
        }
        wave.writeFile();
    }
    

    public static void makeTone(File file, int rate, double f, int A0, double Ae, double k0, double k1, double k2, double k3){
        /*
            file:   Output .wav File
            rate:   采样率
            f:      获得的音高基频
            A0:     预设的开始振幅
            Ae:     默认结束的振幅
            k0~3:   基频和n次泛频的衰减系数
        */
        Wave wave = new Wave(file, 1, 1, rate, 16);
        int n = 0;
        double f1 = 6.267*f, f2 = 17.55*f, f3 = 34.39*f;//泛频
        boolean onf0=true,onf1=true, onf2=true, onf3=true;
        while(onf0 || onf1 || onf2 || onf3){
            double value = 0;
            //写入基频
            if(onf0){
                double A = A0*Math.pow(Math.E, -(k0*n/rate));
                double C = Math.cos(2*Math.PI*f*n/rate);
                if(A>Ae) value+=A*C;
                else onf0=false;
            }
            //写入泛频
            if(onf1){
                double A = A0*Math.pow(Math.E, -(k1*n/rate));
                double C = Math.cos(2*Math.PI*f1*n/rate);
                if(A>Ae) value+=A*C;
                else onf1=false;
            }
            if(onf2){
                double A = A0*Math.pow(Math.E, -(k2*n/rate));
                double C = Math.cos(2*Math.PI*f2*n/rate);
                if(A>Ae) value+=A*C;
                else onf2=false;
            }
            if(onf3){
                double A = A0*Math.pow(Math.E, -(k3*n/rate));
                double C = Math.cos(2*Math.PI*f3*n/rate);
                if(A>Ae) value+=A*C;
                else onf3=false;
            }
            //value+=3*A0;
            wave.chunks.add(new DataChunk161((int)value));
            n++;
        }
        wave.writeFile();
    }

    public static void makeTone(File file, int rate, double f, int A0){
        makeTone(file, rate, f, A0, 10, 2, 10, 90, 150);
    }

    public static void makeMusic(File file, int rate, int A0, double Ae, double k0, double k1, double k2, double k3, int speed, int notenum, double[] notes){
        /*
            file:   Output .wav File
            rate:   采样率
            f:      获得的音高基频
            A0:     预设的开始振幅
            Ae:     默认结束的振幅
            k0~3:   基频和n次泛频的衰减系数
            speed:  节拍
            notenum:一个小节的音符数量
            notes:  乐谱
        */
        Wave wave = new Wave(file, 1, 1, rate, 16);
        List<MakingOneTone> tones = new ArrayList<>();//所有正在合成的音符数据
        //先分析乐谱
        for(int i = 0; i < notes.length; i ++){
            if(notes[i]==NOPE) continue;
            MakingOneTone nowTone = new MakingOneTone();
            nowTone.startPoint = rate*60/speed/(notenum/4)*i;//计算开始的采样点
            nowTone.toneNow = 0;
            nowTone.tonedata = getOneTone(notes[i], rate, A0, Ae, k0, k1, k2, k3);
            nowTone.endPoint = nowTone.startPoint+nowTone.tonedata.length-1;
            nowTone.endFlag = false;
            tones.add(nowTone);
        }
        //进行音乐合成
        long nowPoint = 0;
        while(true){
            int now = 0;
            boolean endFlag = true;
            for(MakingOneTone i : tones){
                if(!i.endFlag){
                    if(nowPoint>=i.startPoint && nowPoint<=i.endPoint){
                        now+=i.tonedata[i.toneNow];
                        i.toneNow++;
                    }
                    else if(nowPoint>i.endPoint){
                        i.endFlag = true;
                    }
                    endFlag = i.endFlag;
                }
            }
            wave.chunks.add(new DataChunk161(now));
            nowPoint++;
            if(endFlag) break;
        }
        wave.writeFile();
    }
    private static int[] getOneTone(double f, int rate, int A0, double Ae, double k0, double k1, double k2, double k3){
        int n = 0;
        double f1 = 6.267*f, f2 = 17.55*f, f3 = 34.39*f;//泛频
        List<Integer> result = new ArrayList<>();
        boolean onf0=true,onf1=true, onf2=true, onf3=true;
        while(onf0 || onf1 || onf2 || onf3){
            double value = 0;
            //写入基频
            if(onf0){
                double A = A0*Math.pow(Math.E, -(k0*n/rate));
                double C = Math.cos(2*Math.PI*f*n/rate);
                if(A>Ae) value+=A*C;
                else onf0=false;
            }
            //写入泛频
            if(onf1){
                double A = A0*Math.pow(Math.E, -(k1*n/rate));
                double C = Math.cos(2*Math.PI*f1*n/rate);
                if(A>Ae) value+=A*C;
                else onf1=false;
            }
            if(onf2){
                double A = A0*Math.pow(Math.E, -(k2*n/rate));
                double C = Math.cos(2*Math.PI*f2*n/rate);
                if(A>Ae) value+=A*C;
                else onf2=false;
            }
            if(onf3){
                double A = A0*Math.pow(Math.E, -(k3*n/rate));
                double C = Math.cos(2*Math.PI*f3*n/rate);
                if(A>Ae) value+=A*C;
                else onf3=false;
            }
            //value+=3*A0;
            result.add((int)value);
            n++;
        }
        int[] retn = new int[result.size()];
        for(int i = 0; i < retn.length; i++){
            retn[i] = result.get(i);
        }
        return retn;
    }
    private static class MakingOneTone{
        public int[] tonedata;//合成的声音数据
        public int toneNow;//当前进行到的index
        public long startPoint;//开始采样点
        public long endPoint;//结束采样点
        public boolean endFlag;//结束标志
    }
}
