package AnimalSmashBros.Gui;

import AnimalSmashBros.Interfaces.Consts;
import AnimalSmashBros.OperaterProcess.UIControl_Start;

import javax.swing.*;

import java.awt.event.KeyListener;

import java.util.List;

public class UIStartPage implements Consts
{
    private DemoPanel UI;
    private KeyListener ukc;
    public UIStartPage()
    {
        UI=new DemoPanel(List.of(new Demo("Resources/Images/UITexture", 0, 0,UIFrames)),TopLevel,true);
        UI.setBounds(0,0,MaxWidth,MaxHeight);
        ukc=new UIControl_Start(this);
    }

    public void OpenUI()
    {
        synchronized (UI_Start_Lock)
        {
            UI.setVisible(true);
            try{UI_Start_Lock.wait();}
            catch (Exception ignored){}
        }
    }

    public void CloseUI()
    {
        synchronized (UI_Start_Lock)
        {
            try{UI_Start_Lock.notify();}
            catch (Exception ignored){}
            UI.setVisible(false);
        }
    }

    public KeyListener getKeyListen()
    {
        System.out.println("监听开始");
        return ukc;
    }

    public JPanel getUI() {return UI;}
    public Integer getLevel() {return TopLevel;}

}

