import greenfoot.*;

public class SpeedBoost extends Actor {
    private int boostDuration = 200;
    private int boostAmount = 2;
    private int timer = 0;
    private int speed = 2;

    public SpeedBoost() {
        // No es necesario establecer la imagen aquí porque Greenfoot lo hace automáticamente.
    }

    public void act() {
        checkCollision();
        if (timer > 0) {
            timer--;
        } else {
            moveDown();
        }
    }

    private void moveDown() {
        setLocation(getX(), getY() + speed);
        if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }

    private void checkCollision() {
        gato player = (gato) getOneIntersectingObject(gato.class);
        if (player != null) {
            applySpeedBoost(player);
            getWorld().removeObject(this);
        }
    }

    private void applySpeedBoost(gato player) {
        player.aumentaVelocidad(boostAmount);
        timer = boostDuration;
    }
}
