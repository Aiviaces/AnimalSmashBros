package AnimalSmashBros.Gui;

import javax.swing.*;
import java.awt.*;

public class Demo
{
    private int x,y,width,height;
    Image img;

    public Demo(String img_src,int x, int y)
    {
        this.img = new ImageIcon(img_src).getImage();
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
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
    public Image getImg() {return img;}
    public void setImg(Image img) {this.img = img;}
    @Override
    public String toString()
    {return "x:%d  y:%d\n".formatted(x,y);}
}
