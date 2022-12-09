package AnimalSmashBros.Interfaces;

public interface Consts {
    int Frames=1000/60;  //帧率
    int MaxWidth=1024;  //窗口宽度
    int MaxHeight=512;  //窗口高度
    double CharactorSize=0.15;  //角色大小
    int GroundLine=415;  //地平线高度
    int GravityVal=2;  //重力值
    int JumpDist=15;   //跳跃距离(帧)
    double JumpPersent=0.5;   //跳跃百分比
    int TopLevel=100;  //顶层UI高度
    int UIFrames=2;  //UI帧率
    int CrashTolerantDist=-46;
    int DeterTime=500;  //战斗判定时间
    int FistBumpTime=200;  //对拳判定时间
    Object UI_Start_Lock = new Object();
}
