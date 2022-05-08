package com.carrotfishstudio.wavemaker;

public class DataChunk161 {
    public final static int size = 2;
    public byte[] data = new byte[2];
    public DataChunk161(int n){
        setValue(n);
    }
    public void setValue(int nv){
        data = Wave.int2bytes(nv, 2);
    }
}
