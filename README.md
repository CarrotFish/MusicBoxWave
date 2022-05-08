# MusicBoxWave
模拟八音盒音色，输入乐谱，输出Wave音乐

# Usage
具体可以参考App.java、MusicEditor.java内的使用

导入库：
import com.carrotfishstudio.wavemaker.WaveMaker;


引用音高频率：
WaveMaker.音名
升调：
WaveMaker.音名r


输出单个音符：
WaveMaker.makeTone(File file, int rate, double f, int A0, double Ae, double k0, double k1, double k2, double k3);
file:   Output .wav File
rate:   采样率
f:      获得的音高基频(即音符频率)
A0:     预设的开始振幅(即响度, 建议用1000的倍数)
Ae:     默认结束的振幅(可以使用定义好的常量 WaveMaker.DEFALT_AE)
k0~3:   基频和n次泛频的衰减系数(可以使用定义好的常量 WaveMaker.DEFALT_K0 WaveMaker.DEFALT_K1 WaveMaker.DEFALT_K2 WaveMaker.DEFALT_K3)


输出曲调
WaveMaker.makeMusic(File file, int rate, int A0, double Ae, double k0, double k1, double k2, double k3, int speed, int notenum, double[] notes)
file:   Output .wav File
rate:   采样率
f:      获得的音高基频(同上)
A0:     预设的开始振幅(同上)
Ae:     默认结束的振幅(同上)
k0~3:   基频和n次泛频的衰减系数(同上)
speed:  速度(每分钟的四分音符数量)
notenum:一个小节的音符数量
notes:  乐谱
