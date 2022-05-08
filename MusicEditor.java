import java.io.File;
import com.carrotfishstudio.wavemaker.WaveMaker;

public class MusicEditor {
    //为了方便直接复制
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
    public static void main(String[] args) {
        int rate=98000, A=5000, speed=90, notenum = 8;
        WaveMaker.makeMusic(new File("try.wav"), rate, A, WaveMaker.DEFALT_AE, WaveMaker.DEFALT_K0, WaveMaker.DEFALT_K1, WaveMaker.DEFALT_K2, WaveMaker.DEFALT_K3, speed, notenum, new double[]{
            NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, A5,   B5,
            C6,   NOPE, NOPE, B5,   C6,   NOPE, E6,   NOPE,
            B5,   NOPE, NOPE, NOPE, NOPE, NOPE, E5,   NOPE,
            A5,   NOPE, NOPE, G5,   A5,   NOPE, C6,   NOPE,
            G5,   NOPE, NOPE, NOPE, NOPE, NOPE, E5,   E5,
            F5,   NOPE, NOPE, E5,   F5,   NOPE, C6,   NOPE,
            E5,   NOPE, NOPE, NOPE, NOPE, NOPE, C6,   C6,
            B5,   NOPE, NOPE, F5r,  F5,   NOPE, B5,   NOPE,
            B5,   NOPE, NOPE, NOPE, NOPE, NOPE, A5,   B5,
            C6,   NOPE, NOPE, B5,   C6,   NOPE, E6,   NOPE,
            B5,   NOPE, NOPE, NOPE, NOPE, NOPE, E5,   NOPE,
            A5,   NOPE, NOPE, G5,   A5,   NOPE, C6,   NOPE,
            G5,   NOPE, NOPE, NOPE, NOPE, NOPE, D5,   E5,
            F5,   NOPE, C6,   B5,   NOPE, NOPE, C6,   D6,
            D6,   NOPE, NOPE, E6,   C6,   NOPE, NOPE, NOPE,
            C6,   B5,   A5,   A5,   B5,   NOPE, G5r,  NOPE,
            A5,   NOPE, NOPE, NOPE, NOPE, NOPE, C6,   D6,
            //重复部分开始
            E6,   NOPE, NOPE, D6,   E6,   NOPE, G6,   NOPE,
            D6,   NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, NOPE,
            C6,   NOPE, C6,   B5,   C6,   NOPE, E6,   NOPE,
            E6,   NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, NOPE,
            A5,   B5,   C6,   NOPE, B5,   C6,   D6,   NOPE,
            C6,   NOPE, NOPE, G5,   G5,   NOPE, NOPE, NOPE,
            F6,   NOPE, E6,   NOPE, D6,   NOPE, C6,   E6,
            NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, E6,   NOPE,
            A6,   NOPE, NOPE, A6,   G6,   NOPE, NOPE, G6,
            E6,   D6,   C6,   NOPE, NOPE, NOPE, C6,   NOPE,
            D6,   NOPE, NOPE, C6,   D6,   NOPE, G6,   NOPE,
            E6,   NOPE, NOPE, NOPE, NOPE, NOPE, E6,   NOPE,
            A6,   NOPE, NOPE, A6,   G6,   NOPE, NOPE, G6,
            E6,   D6,   C6,   NOPE, NOPE, NOPE, C6,   NOPE,
            D6,   NOPE, NOPE, C6,   D6,   NOPE, B5,   A5,
            //重复部分结束
            //进入间奏
            NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, A5,   B5,
            C6,   NOPE, NOPE, B5,   C6,   NOPE, E6,   NOPE,
            B5,   NOPE, NOPE, NOPE, NOPE, NOPE, E5,   NOPE,
            A5,   NOPE, NOPE, G5,   A5,   NOPE, C6,   NOPE,
            G5,   NOPE, NOPE, NOPE, NOPE, NOPE, E5,   NOPE,
            F5,   NOPE, NOPE, E5,   F5,   NOPE, C6,   NOPE,
            E5,   NOPE, NOPE, NOPE, NOPE, NOPE, C6,   NOPE,
            B5,   NOPE, NOPE, F5r,  F5,   NOPE, B5,   NOPE,
            B5,   NOPE, NOPE, NOPE, NOPE, NOPE, A6,   B6,
            C7,   NOPE, NOPE, B6,   C7,   NOPE, E7,   NOPE,
            B6,   NOPE, NOPE, NOPE, NOPE, NOPE, E6,   NOPE,
            A6,   NOPE, NOPE, G6,   A6,   NOPE, C7,   NOPE,
            G6,   NOPE, NOPE, NOPE, NOPE, NOPE, D6,   E6,
            F6,   NOPE, C7,   B6,   NOPE, C7,   NOPE, C7,
            D7,   NOPE, NOPE, E7,   C7,   NOPE, NOPE, NOPE,
            C7,   B6,   A6,   NOPE, B6,   NOPE, G6r,  NOPE,
            A6,   NOPE, NOPE, NOPE, NOPE, NOPE, C6,   D6,
            //间奏结束
            //重复开始
            E6,   NOPE, NOPE, D6,   E6,   NOPE, G6,   NOPE,
            D6,   NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, NOPE,
            C6,   NOPE, C6,   B5,   C6,   NOPE, E6,   NOPE,
            E6,   NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, NOPE,
            A5,   B5,   C6,   NOPE, B5,   C6,   D6,   NOPE,
            C6,   NOPE, NOPE, G5,   G5,   NOPE, NOPE, NOPE,
            F6,   NOPE, E6,   NOPE, D6,   NOPE, C6,   E6,
            NOPE, NOPE, NOPE, NOPE, NOPE, NOPE, E6,   NOPE,
            A6,   NOPE, NOPE, A6,   G6,   NOPE, NOPE, G6,
            E6,   D6,   C6,   NOPE, NOPE, NOPE, C6,   NOPE,
            D6,   NOPE, NOPE, C6,   D6,   NOPE, G6,   NOPE,
            E6,   NOPE, NOPE, NOPE, NOPE, NOPE, E6,   NOPE,
            A6,   NOPE, NOPE, A6,   G6,   NOPE, NOPE, G6,
            E6,   D6,   C6,   NOPE, NOPE, NOPE, C6,   NOPE,
            D6,   NOPE, NOPE, C6,   D6,   NOPE, B5,   A5
            //重复结束
        });
    }

}
