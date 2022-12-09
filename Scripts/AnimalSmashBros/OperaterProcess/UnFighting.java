package AnimalSmashBros.OperaterProcess;

import AnimalSmashBros.Animals.Animal;
import AnimalSmashBros.Gui.Demo;
import AnimalSmashBros.Interfaces.Consts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class UnFighting implements KeyListener,Consts
{
    private long sTime;
    private long eTime;
    private Animal from;
    private Animal enemy;
    private char[] KEYS;
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;
    private boolean jump=false;
    private Fighting Fprocess; //战斗处理

    public UnFighting(Animal from,Animal enemy)
    {
        this.from = from;
        this.enemy = enemy;
        Fprocess=new Fighting(this.from,this.enemy);
        KEYS=from.getKeySet();
        //System.out.printf("上:%b 做:%b 下:%b 右:%b\n",up,left,down,right);
        //重力-自由移动的线程
        Thread Gravity=new Thread(()->
        {
            Timer G=new Timer();
            G.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (jump&& from.isAlive())
                    {
                        from.move(0); //跳
                        if (from.getJump_dist()==0) jump=false;
                    }
                    //System.out.printf("上:%b 左:%b 下:%b 右:%b\n",up,left,down,right);
                    if (!jump&&!from.isOnGround()) from.move(-1); //重力下坠
                }
            },0,Frames);
        });

        Thread move_free=new Thread(()->
        {
            Timer move = new Timer();
            move.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (from.isAlive())
                    {
                        if (left) from.move(2); //左
                        if (right) from.move(4); //右
                        if (up) from.move(5); //上
                    }
                    //if (down) from.move(3); //下
                }
            }, 0, Frames);
        });

        Gravity.start();
        move_free.start();
    }

    public boolean isMeet(Animal p1,Animal p2)
    {
        Demo t1=p1.getTexture();
        Demo t2=p2.getTexture();
        int x1=t1.getX(),x2=t2.getX();
        int y1=t1.getY(),y2=t2.getY();
        int w1=t1.getWidth(),w2=t2.getWidth();
        int h1=t1.getHeight(),h2=t2.getHeight();
        //求两点间距离  勾股定理求斜边长度
        return (Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))-Math.sqrt(w1*w1+h1*h1)-Math.sqrt(w2*w2+h2*h2)/2<=CrashTolerantDist);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        char op=e.getKeyChar();

        if(op==KEYS[0]&&from.isOnGround())
        {
            from.setJump_dist(JumpDist);
            jump=true;
        }

        if(op==KEYS[1]) left=true;
        else if(op==KEYS[3]) right=true;
        else if(op==KEYS[4]) up=true;
        else if(op==KEYS[2]) down=true;
        else if(op==KEYS[5]) {
            from.addFQ(new FightKey(e));
            //System.out.println(from.getFQ(true).getCh()+"  ticks: "+from.getFQ(false).getTime());
            if (from.isAlive()&&enemy.isAlive()&&this.isMeet(from,enemy))
            {
                Fprocess.CenterDeal();
                //System.out.println("撞上了");
            }
            else from.popFQ();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //System.out.printf("松开%c\n",e.getKeyChar());
        char op=e.getKeyChar();

        if (op==KEYS[0]) jump=false;

        if(op==KEYS[1]) left=false;
        else if(op==KEYS[3]) right=false;
        else if(op==KEYS[4]) up=false;
        else if(op==KEYS[2]) down=false;
    }
    @Override
    public void keyPressed(KeyEvent e) {}

}
