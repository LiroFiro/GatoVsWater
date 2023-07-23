import greenfoot.*; 

public class Counter extends Actor
{
    private int value = 0;
    private int target = 0;
    private String text;
    private int stringLength;
    private int animationSpeed = 5;
    private boolean isAnimating = false;

    public Counter() {
        this("");
    }

    public Counter(String prefix) {
        text = prefix;
        stringLength = (text.length() + 2) * 16;

        setImage(new GreenfootImage(stringLength, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F));  
        image.setColor(Color.BLACK);

        updateImage();
    }

    public void act() {
        if (value < target && !isAnimating) {
            value++;
            isAnimating = true;
            animateUpdate();
        } else if (value > target && !isAnimating) {
            value--;
            isAnimating = true;
            animateUpdate();
        }
    }

    public void add(int score) {
        target += score;
    }

    public void subtract(int score) {
        target -= score;
    }

    public int getValue() {
        return value;
    }

    private void updateImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 18);
    }

    private void animateUpdate() {
        GreenfootImage image = getImage();
        Greenfoot.delay(1);

        if (value == target) {
            isAnimating = false;
            updateImage();
            return;
        }

        int change = target - value;
        int step = change / Math.abs(change);

        value += step * animationSpeed;
        updateImage();
    }
}

