import greenfoot.*;  

public class gato extends Actor {
    private int speed;

    public gato(int v) {
        speed = v;
    }

    public void act() {
        CatWorld world = (CatWorld) getWorld();
        if (!world.isGameOver()) {
            if (Greenfoot.isKeyDown("right") && getX() < 460) {
                setLocation(getX() + speed, getY());
            }
            if (Greenfoot.isKeyDown("left") && getX() > 140) {
                setLocation(getX() - speed, getY());
            }
            if (Greenfoot.isKeyDown("up") && getY() > 300) {
                setLocation(getX(), getY() - speed);
            }
            if (Greenfoot.isKeyDown("down") && getY() < 640) {
                setLocation(getX(), getY() + speed);
            }

            checkCollision();
        }
    }

    public void checkCollision() {
        Actor collided = getOneIntersectingObject(Water.class);
        if (collided != null) {
            getWorld().removeObject(collided);
            CatWorld world = (CatWorld) getWorld();
            world.decreaseLives(); // Restar una vida al jugador.
        }
    }

    public void aumenta_velocidad() {
        speed++;
    }
}
