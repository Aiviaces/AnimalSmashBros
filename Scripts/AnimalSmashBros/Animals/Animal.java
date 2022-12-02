package AnimalSmashBros.Animals;

import AnimalSmashBros.Gui.Demo;
import AnimalSmashBros.Interfaces.Actions;

import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public abstract class Animal implements Actions
{
    private String name; //名字
    protected final int type; //种类(猫,狗,...)
    /*血量 = 初始血量*等级倍率 + 技能增强*/
    final private int HP_Init; //初始血量
    /*攻击力 = 初始血量*等级倍率 + 技能增强*/
    private int HP;
    final private int ATK_Init; //初始攻击力
    private int ATK;
    /*防御力 = 初始防御*等级倍率 + 技能增强*/
    final private int DEF_Init; //初始防御力
    private int DEF;
    /*速度 = 初始速度*等级倍率 + 技能增强*/
    final private int SPEED_Init; //初始防御力
    private int SPEED;
    private String img_url; //贴图路径
    Demo texture; //个体变量

    private char[] keySet=new char[]{'w','a','s','d'}; /* 键位 */

    private Queue<KeyEvent> FQ; /* 核心--操作队列 */

    public Animal(int type, int HP_Init, int ATK_Init, int DEF_Init, int SPEED_init,String img_url)
    {
        this.type = type;
        this.HP_Init = HP_Init;
        this.HP = HP_Init;
        this.ATK_Init = ATK_Init;
        this.ATK = ATK_Init;
        this.DEF_Init = DEF_Init;
        this.DEF = DEF_Init;
        SPEED_Init = SPEED_init;
        SPEED = SPEED_init;
        this.img_url=img_url;
        texture = new Demo(img_url,100,100);
        FQ=new LinkedBlockingQueue<>();
    }

    //返回操作队列堆并移除(是否滞留这次操作)
    public KeyEvent getFQ(boolean stay) throws NoSuchElementException
    {return (stay? FQ.element():FQ.remove());}

    public String getImg_url() {return img_url;}

    public Demo getTexture() {return texture;}

    public int getHP() {return HP;}

    public int getATK_Init() {return ATK_Init;}

    public int getATK() {return ATK;}

    public void setName(String name) {this.name = name;}

    public void setHP(int HP) {this.HP = HP;}

    public void setATK(int ATK) {this.ATK = ATK;}

    public void setDEF(int DEF) {this.DEF = DEF;}

    public void setSPEED(int SPEED) {this.SPEED = SPEED;}

    public int getDEF_Init() {return DEF_Init;}

    public int getDEF() {return DEF;}

    public int getSPEED() {return SPEED;}

    public int getSPEED_Init() {return SPEED_Init;}

    public String getName() {return name;}
    public int getType() {return type;}
    public int getHP_Init() {return HP_Init;}
    public void setKeySet(char[] keySet) {this.keySet = keySet;}
    public char[] getKeySet() {return keySet;}

}
