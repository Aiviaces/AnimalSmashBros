package AnimalSmashBros.Gui;

import AnimalSmashBros.Animals.Animal;
import AnimalSmashBros.Animals.Cat;
import AnimalSmashBros.ConstVars.Consts;
import AnimalSmashBros.OperaterProcess.UnFighting;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ShowGame extends JFrame implements Consts
{
    public ShowGame()
    {
        //重命名窗口
        this.setTitle("AnimalSmashBros");
        //设置退出方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小
        this.setSize(MaxWidth,MaxHeight);
        //设置可否修改大小
        this.setResizable(true);


        //建立底层背景个体列表
        ArrayList<Demo> bgs=new ArrayList<>();
        //建立第一层个体列表
        ArrayList<Demo> demos=new ArrayList<>();

        //添加背景个体
        bgs.add(new Demo("Resources/Images/bg.png",0,0));
        //添加第一层个体(设置大小)
        Animal cat=new Cat();
            Animal cat1=new Cat();
            cat1.getTexture().setX(300);
            cat1.getTexture().setY(300);
            cat1.setKeySet(new char[]{'i','j','k','l'});
        demos.add(cat.getTexture());
        demos.add(cat1.getTexture());

        for (Demo demo : demos) demo.setSize(0.2);

        //层级面板
        JLayeredPane layeredPane=new JLayeredPane();
        //建立各层Jpanel面板
        DemoPanel level_0=new DemoPanel(bgs,1);
        DemoPanel level_1=new DemoPanel(demos,5);

        //加入层级面板
        layeredPane.add(level_0,level_0.getLevel());
        layeredPane.add(level_1,level_1.getLevel());

        //设置层级面板属性
        //layeredPane.setBounds(50,50,MaxWidth,MaxHeight);

        //窗口内添加面板组件
        this.setContentPane(layeredPane);
        //设置窗口显示
        this.setVisible(true);

        //开启监听
        UnFighting act=new UnFighting(cat);
        this.addKeyListener(act);

            UnFighting act1=new UnFighting(cat1);
            this.addKeyListener(act1);

        //设置图像更新
        Timer show=new Timer();
        show.schedule(new TimerTask() {
            @Override
            public void run() {
                level_1.repaint();
            }
        },15,1);
    }
}
