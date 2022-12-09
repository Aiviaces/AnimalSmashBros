package AnimalSmashBros.Gui;

import AnimalSmashBros.Animals.Animal;
import AnimalSmashBros.Animals.Cat;
import AnimalSmashBros.Animals.Dog;
import AnimalSmashBros.Interfaces.Consts;
import AnimalSmashBros.OperaterProcess.UnFighting;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/* 没有时间搜集动态贴图放到文件夹里了(放进去对应文件夹就能直接播放对应图片) */
/* 也没时间尝试添加音效了,目前只好这样,UI还不够完善的....处理bug和推翻重写费了不少时间,还请老师见谅 */
/* 六有很多修改空间....测试键位是 猫：[w,a,s,d,r(攻击)] 狗:[i,j,k,l,p(攻击)] */

public class ShowGame extends JFrame implements Consts
{
    /* 传路径参数时要注意,如果是想要是静态的效果,就传文件名的路径,如果是动态图,那就传文件夹路径! */
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
        //设置窗口显示
        this.setVisible(true);
    }

    //建立底层背景个体列表
    private ArrayList<Demo> bgs=new ArrayList<>();
    //建立第一层个体列表
    private ArrayList<Demo> demos=new ArrayList<>();

    //新建第一层个体
    private Animal cat=new Cat();
    private Animal dog=new Dog();
    //新建层级面板
    private JLayeredPane layeredPane=new JLayeredPane();
    //新建顶层UI
    UIStartPage level_top=new UIStartPage();
    KeyListener ukc=level_top.getKeyListen();

    //建立各层Jpanel面板
    DemoPanel level_0=new DemoPanel(bgs,1,false);
    DemoPanel level_1=new DemoPanel(demos,5,false);

    //新建键盘监听
    UnFighting act_cat=new UnFighting(cat,dog);
    UnFighting act_dog=new UnFighting(dog,cat);

    public void Loading()
    {

        //设置层级面板属性
        layeredPane.setBounds(0,0,MaxWidth,MaxHeight);

        //窗口内添加面板组件
        this.setContentPane(layeredPane);

        //添加背景个体
        bgs.add(new Demo("Resources/Images/bg/bg.png",0,0));


        //设置角色位置
        dog.getTexture().setX(500);

        //添加第一层个体
        demos.add(cat.getTexture());
        demos.add(dog.getTexture());
        for (Demo demo : demos) demo.setSize(CharactorSize);


        //添加启动界面UI
        layeredPane.add(level_top.getUI(),level_top.getLevel());

        //启动UI,添加监听
        StartKeyListener(ukc);
        Timer startUI=new Timer();
        startUI.schedule(new TimerTask() {
            @Override
            public void run() {
                level_top.getUI().repaint();
            }
        },0,UIFrames);
        level_top.OpenUI();
        //开始游戏,关闭UI与监听
        startUI.cancel();
        this.removeKeyListener(level_top.getKeyListen());

        //加入层级面板
        layeredPane.add(level_0,level_0.getLevel());
        layeredPane.add(level_1,level_1.getLevel());

        //启动监听
        StartKeyListener(act_dog);
        StartKeyListener(act_cat);

        //设置图像更新
        Timer show=new Timer();
        show.schedule(new TimerTask() {
            @Override
            public void run() {
                StatChange(); //更新状态
                level_1.repaint();
            }
        },0,Frames);
    }

    public void StatChange() //时间不足左更有封装性的代码了,暂且如此,否则需要再设计一下结构
    {
        if (!dog.isAlive())
        {
            Demo t1=dog.getDie_texture();
            t1.setX(dog.getTexture().getX());
            t1.setY(dog.getTexture().getY());
            t1.setWidth(dog.getTexture().getWidth());
            t1.setHeight(dog.getTexture().getHeight());

            demos.set(1,t1);
        }
        if (!cat.isAlive())
        {
            Demo t2=cat.getDie_texture();
            t2.setX(cat.getTexture().getX());
            t2.setY(cat.getTexture().getY());
            t2.setWidth(cat.getTexture().getWidth());
            t2.setHeight(cat.getTexture().getHeight());
            demos.set(0,t2);
        }

    }

    public void StartKeyListener(KeyListener obj) //统一添加,方便额外处理
    {
        this.addKeyListener(obj);
    }


}
