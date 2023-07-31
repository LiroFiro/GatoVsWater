import greenfoot.*;

public class SpeedBoost extends Actor {
    private int boostDuration = 200; // Duración del aumento de velocidad (en ciclos de act).
    private int boostAmount = 2; // Cuánto se aumenta la velocidad.
    private int timer = 0; // Contador para medir la duración del power-up.

    public SpeedBoost() {
        setImage("speed_boost.png"); // Cambia "speed_boost.png" por el nombre de tu imagen del power-up.
    }

    public void act() {
        checkCollision();
        if (timer > 0) {
            timer--;
        } else {
            revertSpeedBoost();
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

    private void revertSpeedBoost() {
        gato player = (gato) getOneIntersectingObject(gato.class);
        if (player != null) {
            player.revertVelocidad(boostAmount);
        }
    }
}
