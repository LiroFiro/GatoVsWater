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

        Actor powerUp = getOneIntersectingObject(SpeedBoost.class);
        if (powerUp != null) {
            getWorld().removeObject(powerUp);
            applySpeedBoost();
        }
    }

    public void aumentaVelocidad(int amount) {
        speed += amount;
    }

    public void revertVelocidad(int amount) {
        speed -= amount;
    }

    private void applySpeedBoost() {
        CatWorld world = (CatWorld) getWorld();
        world.increaseScore(20); // Puedes ajustar la cantidad de puntos obtenidos por recoger el power-up.
        aumentaVelocidad(2); // Puedes ajustar la cantidad de aumento de velocidad por el power-up.
        Greenfoot.delay(100); // Duración del power-up (en ciclos de act).
        revertVelocidad(2); // Después de la duración, revertir el aumento de velocidad.
    }
}
