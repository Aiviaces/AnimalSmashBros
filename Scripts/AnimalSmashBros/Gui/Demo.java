package AnimalSmashBros.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


class Gif
{
    // Image数组,设置Timer循环切换展示的贴图
    private Image[] gif;
    private int len;
    private int disp_speed;
    private int i;
    Gif(String imgs_src,int disp_speed)
    {
        String[] srcs=new File(imgs_src).list();
        //System.out.println(imgs_src);
        //System.out.println(Arrays.toString(srcs));
        //Debug
        assert srcs != null;
        gif=new Image[srcs.length];
        len=srcs.length; i=0;
        this.disp_speed=disp_speed;
        for (int i = 0; i < len; i++) {
            srcs[i]="%s/%s".formatted(imgs_src,srcs[i]);
            //System.out.println(srcs[i]);
            //Debug
            gif[i]=new ImageIcon(srcs[i]).getImage();
        }
        Timer img_iterator=new Timer();
        int it_speed=(int)(1000D/disp_speed);
        System.out.println("帧率: "+disp_speed+" 每 "+it_speed+"ms 播放一帧");
        img_iterator.schedule(new TimerTask() {
            //迭代图片计时
            @Override
            public void run() {i=(i+1)%len;}
        },it_speed,it_speed);
    }

    // 整个动态图的大小由第一张图片决定 !
    public Image[] getGif() {return gif;}
    public void setGif(Image[] gif) {this.gif = gif;}
    public int getWidth(){return this.gif[0].getWidth(null);}
    public int getHeight(){return this.gif[0].getHeight(null);}

    public void setDisp_speed(int disp_speed)
    {this.disp_speed = disp_speed;}

    public Image getPart(){
        //System.out.println("展示Gif["+i+"]");
        return gif[i];}
}

public class Demo
{
    private int x,y,width,height;
    private Gif gif;
    private Image img;
    private boolean is_gif;
    public Demo(String img_src,int x, int y,int disp_speed)
    {
        /* 图片路径,x坐标,y坐标,动态图帧率(不填代表静态图,另外构造) */
        this.x = x;
        this.y = y;
        gif=new Gif(img_src,disp_speed);
        this.width = gif.getWidth();
        this.height = gif.getHeight();
        is_gif=true;
    }
    public Demo(String img_src,int x,int y)
    {
        this.x = x;
        this.y = y;
        img=new ImageIcon(img_src).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        is_gif=false;
    }
    //设置整体大小
    public void setSize(double persent)
    {
        width*=persent;
        height*=persent;
    }

    //getter和setter
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}
    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}
    public Gif getGif() {return gif;}
    public void setGif(Gif gif) {this.gif = gif;}

    public void setImg(Image img) {this.img = img;}

    public Image getImg(){return is_gif?gif.getPart():img;}
    @Override
    public String toString()
    {return "x:%d  y:%d\n".formatted(x,y);}
}
