package AnimalSmashBros.OperaterProcess;

import AnimalSmashBros.Animals.Animal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class UnFighting implements KeyListener
{
    private long sTime;
    private long eTime;
    private Animal from;
    private char[] KEYS;
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;

    public UnFighting(Animal from)
    {
        this.from = from;
        KEYS=from.getKeySet();
        //System.out.printf("上:%b 做:%b 下:%b 右:%b\n",up,left,down,right);
        //自由移动的线程
        Thread move_random = new Thread(() -> {
            Timer move = new Timer();
            move.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (up) from.move(1);
                    if (left) from.move(3);
                    if (down) from.move(2);
                    if (right) from.move(4);
                    //System.out.printf("上:%b 做:%b 下:%b 右:%b\n",up,left,down,right);
                }
            }, 15, 1);
        });
        move_random.start();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        char op=e.getKeyChar();
        if(op==KEYS[0]) up=true;
        else if(op==KEYS[1]) left=true;
        else if(op==KEYS[2]) down=true;
        else if(op==KEYS[3]) right=true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //System.out.printf("松开%c\n",e.getKeyChar());
        char op=e.getKeyChar();
        if(op==KEYS[0]) up=false;
        else if(op==KEYS[1]) left=false;
        else if(op==KEYS[2]) down=false;
        else if(op==KEYS[3]) right=false;
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    public Animal getFrom() {return from;}
}
