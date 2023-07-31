import greenfoot.*;

public class CatWorld extends World {
    private Counter scoreCounter;
    private Counter levelCounter;
    private int catSpeed;
    private int numPasses;
    private int numPassesPerLevel;
    private int numRivals;
    private int numLives;
    private int initialLives = 3;
    private gato alonso;
    private boolean isGameOver;

    public CatWorld() {
        super(600, 700, 1);
        numPasses = 0;
        numPassesPerLevel = 4;
        catSpeed = 2;
        numLives = initialLives;
        isGameOver = false;
        scoreCounter = new Counter("Score: ");
        levelCounter = new Counter("Level: ");
        levelCounter.add(1);
        alonso = new gato(catSpeed);
        addObject(alonso, 300, 600);
        addObject(levelCounter, 205, 90);
        addObject(scoreCounter, 205, 60);
        updateLives();
    }

    public void act() {
        if (!isGameOver) {
            increaseDifficulty();
            addRivals();
        }
    }

    public int getRandomNumber(int start, int end) {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }

    public void increaseScore(int value) {
        scoreCounter.add(value);
    }

    public void increasePasses() {
        numPasses++;
    }

    public void decreaseRivals() {
        numRivals--;
    }

    public void decreaseLives() {
        numLives--;
        updateLives();
        if (numLives <= 0) {
            isGameOver = true; // Marcar el juego como terminado.
            Greenfoot.stop(); // Detener el juego cuando se acaban las vidas.
        } else {
            alonso.setLocation(getWidth() / 2, getHeight() - 50); // Colocar al gato en la posición inicial.
            numRivals = 0; // Restablecer el contador de rivales cuando el gato pierde una vida.
        }
    }

    public void increaseDifficulty() {
        if (numPasses == numPassesPerLevel) {
            numPasses = 0;
            numPassesPerLevel = numPassesPerLevel + 2;
            catSpeed++;
            levelCounter.add(1);
        }
    }

    public void addRivals() {
        if (numRivals == 0) {
            int lane = getRandomNumber(0, 3);
            if (lane == 0) {
                addObject(new Water(catSpeed), 180, 80);
            } else if (lane == 1) {
                addObject(new Water(catSpeed), 290, 80);
            } else {
                addObject(new Water(catSpeed), 410, 80);
            }
            lane++;
            lane = lane % 3;
            if (lane == 0) {
                addObject(new Water(catSpeed), 180, 80);
            } else if (lane == 1) {
                addObject(new Water(catSpeed), 290, 80);
            } else {
                addObject(new Water(catSpeed), 410, 80);
            }
            numRivals = 2;
        }
    }

    private void updateLives() {
        removeObjects(getObjects(Heart.class)); // Eliminar todos los objetos de tipo Heart del mundo.
        for (int i = 0; i < numLives; i++) {
            Heart heart = new Heart();
            addObject(heart, 30 + i * 40, 25);
            heart.setRotation(0);
            GreenfootImage heartImage = heart.getImage();
            heartImage.scale(30, 30); // Cambia el tamaño de la imagen del corazón.
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
