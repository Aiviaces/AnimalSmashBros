package AnimalSmashBros.Animals;

import AnimalSmashBros.Interfaces.Consts;
import AnimalSmashBros.Gui.Demo;

public class Dog extends Animal implements Consts
{
    private Demo Texture=super.getTexture();
    public Dog()
    {super("狗",1,100, 20, 5, 40,"Resources/Images/dog",2,new char[]{'i','j','k','l','p','['});}
}
