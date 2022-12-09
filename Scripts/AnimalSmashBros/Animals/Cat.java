package AnimalSmashBros.Animals;

import AnimalSmashBros.Interfaces.Consts;
import AnimalSmashBros.Gui.Demo;

/* 两个动物类比较重复,是因为考虑到可以再所有功能中加入技能效果什么的 */

public class Cat extends Animal implements Consts
{
    private Demo Texture=super.getTexture();
    public Cat()
    {super("猫",0,100, 10, 5, 70,"Resources/Images/cat",4,new char[]{'w','a','s','d','e','r'});}
}
