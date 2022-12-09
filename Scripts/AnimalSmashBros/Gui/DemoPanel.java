package AnimalSmashBros.Gui;

import AnimalSmashBros.Interfaces.Consts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DemoPanel extends JPanel implements Consts
{
    private Integer level; //面板层级
    private List<Demo> entities=new ArrayList<>(); //个体列表
    public DemoPanel(List<Demo> demos,int level,boolean Opaque) //构造函数-统一设置属性
    {
        entities=demos;
        this.level=level*100;
        this.setBounds(0,0,MaxWidth,MaxHeight);
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) //重写
    {
        super.paint(g);
        for (Demo i : entities) {
            g.drawImage(i.getImg(),
                    i.getX(),i.getY(),
                    i.getWidth(),i.getHeight(),
                    null);
        }
    }

    public Integer getLevel() {return level;}

    public void setEntities(List<Demo> entities) {this.entities = entities;}

    public void setLevel(int level) {this.level = level;}
}
