import greenfoot.*;

public class gato extends Actor {
    private int speed;

    public gato(int v){
        speed = v;
    }

    public void act() 
    {
        if(Greenfoot.isKeyDown("right")){
            if(getX() < 460)
                setLocation(getX() + speed, getY());
        }
        if(Greenfoot.isKeyDown("left")){
            if(getX() > 140)
                setLocation(getX() - speed, getY()); 
        }
        if(Greenfoot.isKeyDown("up")){
            if(getY() > 300)
                setLocation(getX() , getY() - speed); 
        }
        if(Greenfoot.isKeyDown("down")){
            if(getY() < 640)
                setLocation(getX() , getY() + speed); 
        }

        checkCollision();
    }

    public void checkCollision(){
        Actor collided = getOneIntersectingObject(Water.class);
        if (collided != null)
        {
            CatWorld world = (CatWorld) getWorld();
            world.decreaseLives(); // Restar una vida al colisionar con el agua.
            world.removeObject(this); // Eliminar el gato del mundo.
        }
    }

    public void aumentaVelocidad(int amount){
        speed += amount;
    }
}
