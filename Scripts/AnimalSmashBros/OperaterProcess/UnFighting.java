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
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;

    public UnFighting(Animal from)
    {
        this.from = from;
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
        keyPressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //System.out.printf("按下%c\n",e.getKeyChar());
        char op=e.getKeyChar();
        switch (op)
        {
            case 'a'->left=true;
            case 'w'->up=true;
            case 'd'->right=true;
            case 's'->down=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //System.out.printf("松开%c\n",e.getKeyChar());
        char op=e.getKeyChar();
        switch (op)
        {
            case 'a'->left=false;
            case 'w'->up=false;
            case 'd'->right=false;
            case 's'->down=false;
        }
    }

    public Animal getFrom() {return from;}
}
