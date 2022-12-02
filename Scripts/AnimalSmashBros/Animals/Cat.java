package AnimalSmashBros.Animals;

import AnimalSmashBros.ConstVars.Consts;
import AnimalSmashBros.Gui.Demo;

public class Cat extends Animal implements Consts
{
    private Demo Texture=super.getTexture();
    public Cat()
    {super(0,100, 10, 5, 30,"Resources/Images/cat.png");}

    @Override
    public void move(int toward)
    {
        System.out.print("触发移动"+toward);
        System.out.println(this.Texture);
        int x=Texture.getX();
        int y=Texture.getY();
        int dist=super.getSPEED()/10;
        switch (toward)
        {
            case 1->
            {if(y-dist>=0) Texture.setY(y-dist);}
            case 2->
            {if(y+dist<MaxHeight-Texture.getHeight()) Texture.setY(y+dist);}
            case 3->
            {if(x-dist>=0) Texture.setX(x-dist);}
            case 4->
            {if(x+dist<MaxWidth-Texture.getWidth()) Texture.setX(x+dist);}
        }
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
    public void HPchange(boolean Up_Or_Down) {

    }

    @Override
    public void ATKchange(boolean Up_Or_Down) {

    }

    @Override
    public void DEFchange(boolean Up_Or_Down) {

    }

    @Override
    public void SPEEDchange(boolean Up_Or_Down) {

    }

}
