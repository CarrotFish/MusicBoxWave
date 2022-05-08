# MusicBoxWave
模拟八音盒音色，输入乐谱，输出Wave音乐

# Usage
具体可以参考App.java、MusicEditor.java内的使用<br />
<br />
导入库：<br />
`import com.carrotfishstudio.wavemaker.WaveMaker;`<br />
<br />
<br />
引用音高频率：<br />
`WaveMaker.音名`<br />
升调：<br />
`WaveMaker.音名r`<br />
<br />
<br />
输出单个音符：<br />
`WaveMaker.makeTone(File file, int rate, double f, int A0, double Ae, double k0, double k1, double k2, double k3);`<br />
file:   Output .wav File<br />
rate:   采样率<br />
f:      获得的音高基频(即音符频率)<br />
A0:     预设的开始振幅(即响度, 建议用1000的倍数)<br />
Ae:     默认结束的振幅(可以使用定义好的常量 WaveMaker.DEFALT_AE)<br />
k0\~3:   基频和n次泛频的衰减系数(可以使用定义好的常量 WaveMaker.DEFALT_K0 WaveMaker.DEFALT_K1 WaveMaker.DEFALT_K2 WaveMaker.DEFALT_K3)<br />
<br />
<br />
输出曲调：<br />
`WaveMaker.makeMusic(File file, int rate, int A0, double Ae, double k0, double k1, double k2, double k3, int speed, int notenum, double[] notes);`<br />
file:   Output .wav File<br />
rate:   采样率<br />
f:      获得的音高基频(同上)<br />
A0:     预设的开始振幅(同上)<br />
Ae:     默认结束的振幅(同上)<br />
k0\~3:   基频和n次泛频的衰减系数(同上)<br />
speed:  速度(每分钟的四分音符数量)<br />
notenum:一个小节的音符数量<br />
notes:  乐谱<br />
<br />
