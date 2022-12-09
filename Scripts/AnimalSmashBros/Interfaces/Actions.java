package AnimalSmashBros.Interfaces;

public interface Actions
{
    void move(int toward); /*移动(朝向[1,2,3,4]->[上下左右])*/
    void atack(); /*攻击行为*/
    void dodge(); /*闪避行为*/
    void fistbump(); /*对拳行为--对拳是可以发动"替身"*/

    /* 状态变化 */
    void HPchange(boolean Up_Or_Down,int val);
    void ATKchange(boolean Up_Or_Down,int val);
    void DEFchange(boolean Up_Or_Down,int val);
    void SPEEDchange(boolean Up_Or_Down,int val);
}
