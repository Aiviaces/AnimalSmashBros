package AnimalSmashBros.OperaterProcess;

import AnimalSmashBros.Animals.Animal;
import AnimalSmashBros.Interfaces.Consts;


//没有除遍历外更高效的判断多玩家碰撞的版发,只好缩减成2p对战了
public class Fighting implements Consts
{
    private Animal p1;
    private Animal p2;
    private char[] keys_p1;
    private char[] keys_p2;





    public Fighting(Animal p1,Animal p2)
    {
        this.p1=p1;
        this.p2=p2;
        keys_p1=p1.getKeySet();
        keys_p2=p2.getKeySet();
    }
    public void CenterDeal()
    {
        FightKey t1=p1.getFQ(true);
        FightKey t2=p2.getFQ(true);
        //System.out.println(t1+" "+t2);
        //暂且只写战斗,不弄闪避什么的,时间不足了
        if (t1==null||t2==null)
        {
            if (t1!=null&&t1.getCh()==keys_p1[5])
            {
                p2.HPchange(false,p1.getATK()-p2.getDEF());
                System.out.println(p2.getName()+" 受到攻击!  HP ( "+p2.getHP_Init()+" / "+p2.getHP()+" )");
            }
            else if (t2!=null&&t2.getCh()==keys_p2[5])
            {
                p1.HPchange(false,p2.getATK()-p1.getDEF());
                System.out.println(p1.getName()+" 受到攻击!  HP ( "+p1.getHP_Init()+" / "+p1.getHP()+" )");
            }
        }
        else
        {
            long time1=t1.getTime();
            long time2=t2.getTime();
            //if (Math.abs(time1-time2)<=FistBumpTime) TODO 对拳
            if(time1-time2<DeterTime)
            {
                p2.HPchange(false,p1.getATK()-p2.getDEF());
                System.out.println(p2.getName()+" 受到攻击!  HP ( "+p2.getHP_Init()+" / "+p2.getHP()+" )");
            }
            else if (time1-time2>DeterTime)
            {
                p1.HPchange(false,p2.getATK()-p1.getDEF());
                System.out.println(p1.getName()+" 受到攻击!  HP ( "+p1.getHP_Init()+" / "+p1.getHP()+" )");
            }
        }
        p1.popFQ();
        p2.popFQ();
    }
}
