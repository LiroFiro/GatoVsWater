import greenfoot.*; 

public class CatWorld extends World
{

    private Counter scoreCounter;
    private Counter levelCounter;
    
    private int catSpeed;
    private int numPasses;
    private int numPassesPerLevel;
    private gato alonso;
    private int numRivals;
    
    public CatWorld()
    {    

        super(600, 700, 1);
        
       numPasses = 0;
       numPassesPerLevel = 4;
       catSpeed = 2;
       
       scoreCounter = new Counter("Score: ");
       levelCounter = new Counter("Level: ");
       levelCounter.add(1);
       alonso = new gato(catSpeed);
       
       addObject(alonso, 300, 600);
       addObject(levelCounter, 205, 90);
       addObject(scoreCounter, 205, 60);
    }
    
    public void act(){
        increaseDifficulty();
        addRivals();
    }
    
    public int getRandomNumber(int start, int end){
       int normal = Greenfoot.getRandomNumber(end - start + 1);
       return normal + start;
    }
    
    public void increaseScore(int value){
        scoreCounter.add(value);
    }
    
    public void increasePasses(){
        numPasses++;
    }
    
    public void decreaseRivals(){
        numRivals--;
    }
    
    
    public void increaseDifficulty(){
        if(numPasses == numPassesPerLevel){
            numPasses = 0;
            numPassesPerLevel = numPassesPerLevel + 2;
            catSpeed++;
            levelCounter.add(1);

        }
    }
   
    public void addRivals(){
        
        if(numRivals == 0){
            
            int lane = getRandomNumber(0, 3);
            
            if(lane == 0){
                addObject(new Water(catSpeed), 180, 80);
            }
            else if(lane == 1){
                addObject(new Water(catSpeed), 290, 80);
            }
            else{
                addObject(new Water(catSpeed), 410, 80);
            }
            
            lane++;
            lane = lane % 3;
            
            if(lane == 0){
                addObject(new Water(catSpeed), 180, 80);
            }
            else if(lane == 1){
                addObject(new Water(catSpeed), 290, 80);
            }
            else{
                addObject(new Water(catSpeed), 410, 80);
            }
            
            
            numRivals = 2;
            }
    }
}






            
        
        
