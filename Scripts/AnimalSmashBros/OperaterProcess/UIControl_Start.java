package AnimalSmashBros.OperaterProcess;

import AnimalSmashBros.Gui.UIStartPage;
import AnimalSmashBros.Interfaces.Consts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UIControl_Start implements KeyListener, Consts
{
    private UIStartPage UI;
    public UIControl_Start(UIStartPage UI) {this.UI=UI;}
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("点按键,关UI!!!");
        UI.CloseUI();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}