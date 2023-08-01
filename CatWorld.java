
import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

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
    private boolean spawnRivals;
    private boolean spawnPowerUp;
    private int powerUpSpawnDelay = 500; // Retraso en ciclos act antes de permitir que aparezcan nuevos power-ups.
    private int powerUpSpawnTimer = 0;

    public CatWorld() {
        super(600, 700, 1);
        numPasses = 0;
        numPassesPerLevel = 4;
        catSpeed = 2;
        numLives = initialLives;
        isGameOver = false;
        spawnRivals = false;
        spawnPowerUp = false;
        scoreCounter = new Counter("Score: ");
        levelCounter = new Counter("Level: ");
        levelCounter.add(1);
        alonso = new gato(catSpeed);
        addObject(alonso, getWidth() / 2, getHeight() - 50);
        addObject(levelCounter, 205, 90);
        addObject(scoreCounter, 205, 60);
        updateLives();
    }

    public void act() {
        if (!isGameOver) {
            increaseDifficulty();
            if (spawnRivals) {
                generateRivals();
                spawnRivals = false;
            }
            addRivals();
            if (spawnPowerUp) {
                generatePowerUp();
                spawnPowerUp = false;
            }
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

        // Crear una lista temporal para almacenar los objetos de tipo Water y SpeedBoost.
        List<Water> waters = getObjects(Water.class);
        List<SpeedBoost> powerUps = getObjects(SpeedBoost.class);

        // Eliminar los objetos de tipo Water, excepto el gato.
        List<Water> watersToRemove = new ArrayList<>();
        for (Water water : waters) {
            if (water != null && !water.equals(alonso)) {
                watersToRemove.add(water);
            }
        }
        removeObjects(watersToRemove);

        // Eliminar los objetos de tipo SpeedBoost.
        removeObjects(powerUps);

        addObject(new gato(catSpeed), getWidth() / 2, getHeight() - 50); // Agregar un nuevo gato al mundo.
    }
}

            }
        }
        removeObjects(watersToRemove);

        // Eliminar los objetos de tipo SpeedBoost.
        removeObjects(powerUps);

        addObject(new gato(catSpeed), getWidth() / 2, getHeight() - 50); // Agregar un nuevo gato al mundo.
    }
        removeObjects(watersToRemove);

        // Eliminar los objetos de tipo SpeedBoost.
        removeObjects(powerUps);
    }
}


    public void increaseDifficulty() {
        if (numPasses == numPassesPerLevel) {
            numPasses = 0;
            numPassesPerLevel = numPassesPerLevel + 2;
            catSpeed++;
            levelCounter.add(1);
            spawnPowerUp = true;
        }
    }

    private void generateRivals() {
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

    private void generatePowerUp() {
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        // Ajustar las coordenadas para que estén dentro de los límites del mundo.
        x = Math.min(Math.max(x, 0), getWidth() - 1);
        y = Math.min(Math.max(y, 0), getHeight() - 1);
        if (!isLocationOccupiedByWater(x, y) && !isLocationOccupiedByPowerUp(x, y)) {
            addObject(new SpeedBoost(), x, y);
        }
        powerUpSpawnTimer = powerUpSpawnDelay; // Reiniciar el temporizador de generación de power-ups.
    }

    private boolean isLocationOccupiedByWater(int x, int y) {
        List<Water> waters = getObjectsAt(x, y, Water.class);
        return !waters.isEmpty();
    }

    private boolean isLocationOccupiedByPowerUp(int x, int y) {
        List<SpeedBoost> powerUps = getObjectsAt(x, y, SpeedBoost.class);
        return !powerUps.isEmpty();
    }

    private void updateLives() {
        removeObjects(getObjects(Heart.class));
        for (int i = 0; i < numLives; i++) {
            Heart heart = new Heart();
            addObject(heart, 30 + i * 40, 25);
            heart.setRotation(0);
            GreenfootImage heartImage = heart.getImage();
            heartImage.scale(30, 30);
        }
    }

    public boolean isGameOver() {
        return isGameOver;
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
    private void resetGame() {
        removeObjects(getObjects(null)); // Eliminar todos los objetos del mundo.
        numPasses = 0;
        numPassesPerLevel = 4;
        catSpeed = 2;
        numLives = initialLives;
        scoreCounter.setValue(0); // Restablecer el contador de puntuación.
        levelCounter.setValue(1); // Restablecer el contador de nivel.
        alonso = new gato(catSpeed);
        addObject(alonso, 300, 600);
        addObject(levelCounter, 205, 90);
        addObject(scoreCounter, 205, 60);
        updateLives();
        isGameOver = false;
    }
    public void addPowerUp() {
        if (Greenfoot.getRandomNumber(100) < 5) { // 5% de probabilidad de generar el power-up.
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = 0;
            addObject(new SpeedBoost(), x, y);
        }
    }    
}
