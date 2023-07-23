import greenfoot.*; 

public class Water extends Actor
{
    private int speed;
    
    public Water(int v) {
        speed = v;
    }
   
    public void act() 
    {
        setLocation(getX(), getY() + speed);
        if (getY() >= getWorld().getHeight() - 1) {
            CatWorld world = (CatWorld) getWorld();
            world.removeObject(this);
            world.increaseScore(10);
            world.decreaseRivals();
            world.increasePasses();
        }
    }    
}

