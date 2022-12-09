package AnimalSmashBros.Animals;

import AnimalSmashBros.Interfaces.Consts;
import AnimalSmashBros.Gui.Demo;
import AnimalSmashBros.Interfaces.Actions;
import AnimalSmashBros.OperaterProcess.FightKey;


import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public abstract class Animal implements Actions, Consts
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

    private boolean die;

    private int ACCELERATION; //加速度

    private int dist;
    private int jump_dist;
    private String img_url; //贴图路径
    Demo texture; //当前贴图变量
    Demo normal_texture; //通常贴图
    Demo fight_texture; //战斗贴图
    Demo die_texture; //死亡贴图

    private char[] keySet; /* 键位 */

    private Queue<FightKey> FQ; /* 核心--操作队列 */

    //若填了 dispDFS 参数,代表贴图是动态播放的
    public Animal(String name,int type, int HP_Init, int ATK_Init, int DEF_Init, int SPEED_init,String img_url,char[] key_set)
    {
        this.name=name;
        this.type = type;
        this.HP_Init = HP_Init;
        this.HP = HP_Init;
        this.ATK_Init = ATK_Init;
        this.ATK = ATK_Init;
        this.DEF_Init = DEF_Init;
        this.DEF = DEF_Init;
        this.SPEED_Init = SPEED_init;
        this.SPEED = SPEED_init;
        this.die=false;
        this.ACCELERATION=0;
        this.jump_dist=JumpDist;
        this.img_url=img_url;
        this.FQ=new LinkedBlockingQueue<>();
        this.keySet=key_set;

        this.normal_texture = new Demo(img_url,100,100);
        this.die_texture = new Demo("Resources/Images/defaults/die.png",100,100);
        this.die_texture.setSize(CharactorSize);
        this.texture=normal_texture;
    }
    public Animal(String name,int type, int HP_Init, int ATK_Init, int DEF_Init, int SPEED_init,String img_url,int disFPS,char[] key_set)
    {
        this(name,type, HP_Init, ATK_Init, DEF_Init, SPEED_init, img_url, key_set);
        this.normal_texture = new Demo(img_url,100,100,disFPS);
        //this.die_texture = new Demo("Resources/Images/defaults",100,100,disFPS); //TODO 死亡动态贴图
        this.texture=normal_texture;
    }


    public void move(int toward) // [1,2,3,4:上,左,下,右]
    {
        /*System.out.print("触发移动"+toward);System.out.println(this.texture);*/
        int x=texture.getX();
        int y=texture.getY();
        dist=this.SPEED/10;
        switch (toward)
        {
            //case 1-> //上
            //{if(y-dist>=0) texture.setY(y-dist);}
            //case 3-> //下
            //{if(y+dist<MaxHeight-texture.getHeight()) texture.setY(y+dist);}

            case 2-> //左
            {if(x-dist>=0) texture.setX(x-dist);}
            case 4-> //右
            {if(x+dist<MaxWidth-texture.getWidth()) texture.setX(x+dist);}

            case -1-> //重力下坠
            {
                int gravity_down_dist=Math.min(y+(int)(JumpPersent*GravityVal*jump_dist),GroundLine-texture.getHeight());
                //System.out.println("下坠 "+gravity_down_dist);
                if (gravity_down_dist!=GroundLine)
                {
                    //System.out.println("下落值: "+jump_dist);
                    //if (jump_dist==JumpDist) jump_dist=15;
                    texture.setY(gravity_down_dist);
                    jump_dist=Math.min(jump_dist+1,JumpDist);
                }
            }
            case 0-> //跳
            {
                int jump_dist_inc=Math.max(y-(int)(JumpPersent*GravityVal*jump_dist),0);
                if (jump_dist_inc!=0)
                {
                    texture.setY(jump_dist_inc);
                    jump_dist=Math.max(0,jump_dist-1);
                }
            }
        }
    }

    //返回操作队列堆并移除(是否滞留这次操作)
    public FightKey getFQ(boolean stay) throws NoSuchElementException
    {
        if (!FQ.isEmpty()) return stay? FQ.element():FQ.remove();
        return null;
    }

    //添加战斗键盘事件-队尾
    public void addFQ(FightKey e){this.FQ.add(e);}
    //溢出战斗键盘事件-队首
    public void popFQ(){if (!this.FQ.isEmpty())this.FQ.remove();}

    public boolean isOnGround(){return texture.getY()==GroundLine-texture.getHeight();}

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

    public boolean isAlive() {return !die;}

    public void setDie(boolean die) {this.die = die;}

    public void setACCELERATION(int ACCELERATION) {this.ACCELERATION = ACCELERATION;}

    public int getDEF_Init() {return DEF_Init;}

    public int getDEF() {return DEF;}

    public int getSPEED() {return SPEED;}

    public int getSPEED_Init() {return SPEED_Init;}

    public int getACCELERATION() {return ACCELERATION;}
    public void setDist(int dist) {this.dist = dist;}

    public void setJump_dist(int jump_dist) {this.jump_dist = jump_dist;}

    public double getJump_dist() {return jump_dist;}

    public String getName() {return name;}

    public int getType() {return type;}

    public int getHP_Init() {return HP_Init;}

    public void setKeySet(char[] keySet) {this.keySet = keySet;}

    public char[] getKeySet() {return keySet;}

    public Demo getFight_texture() {return fight_texture;}

    public Demo getDie_texture() {return die_texture;}

    public Demo getNormal_texture() {return normal_texture;}

    public void setTexture(Demo texture)
    {
        this.texture = texture;
    }

    @Override
    public void atack() {

    }

    @Override
    public void dodge() {

    }

    @Override
    public void fistbump() {

    }

    @Override
    public void HPchange(boolean Up_Or_Down,int val) {
        this.HP=Math.max(0,this.HP-val);
        if (this.HP==0)
        {
            die=true;
            System.out.println(name+" 死了!");
            //this.texture=this.die_texture;
        }
    }

    @Override
    public void ATKchange(boolean Up_Or_Down,int val) {

    }

    @Override
    public void DEFchange(boolean Up_Or_Down,int val) {

    }

    @Override
    public void SPEEDchange(boolean Up_Or_Down,int val) {

    }

}
