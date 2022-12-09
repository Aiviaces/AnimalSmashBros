package AnimalSmashBros.OperaterProcess;

import java.awt.event.KeyEvent;

public class FightKey
{
    private char ch;
    private long time;
    public FightKey(KeyEvent e)
    {
        this.ch = e.getKeyChar();
        this.time = System.currentTimeMillis();
    }
    public char getCh() {return ch;}
    public long getTime() {return time;}
}
