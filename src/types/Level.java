package types;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import main.Main;
import solids.Finish;
// Level class.
public class Level {
  // Elapsed time.
  public int time = 0;
  // Current score.
  public int score = 0;
  // Level background.
  private Image background;
  // Map items in the level.
  private ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  // Active projectiles in the level.
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  // Active enemies in the level.
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  // Blocks in the level.
  private ArrayList<Block> blocks = new ArrayList<Block>();
  // Number of current level.
  private int levelNumber;
  // Finish line of level.
  private Finish finish;
  // Elapsed time timer.
  private Timer timeTimer;
  // Time bonus applied on completion.
  private int timeBonus;
  // Level boss if any.
  private Boss boss;
  
  public Level(Image background) {
    
    this.setBackground(background);
    this.timeTimer = new Timer();
    // Increment elapsed time.
    timeTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        
        Main.getGame().getCurrentLevel().time++;
        
      }
    }, 1000, 1000);
    
  }
  
  public void end(boolean success) {
    // Cancel timer and proceed to end screen.
    this.timeTimer.cancel();
    this.timeTimer.purge();
    this.timeTimer = null;
    Main.getGame().setState(success ? (byte) 6 : (byte) 7);
    
  }

  public Image getBackground() {
    return background;
  }

  public void setBackground(Image background) {
    this.background = background;
  }

  public ArrayList<MapItem> getMapItems() {
    return mapItems;
  }

  public void setMapItems(ArrayList<MapItem> mapItems) {
    this.mapItems = mapItems;
  }

  public ArrayList<Projectile> getProjectiles() {
    return projectiles;
  }

  public void setProjectiles(ArrayList<Projectile> projectiles) {
    this.projectiles = projectiles;
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  public void setEnemies(ArrayList<Enemy> enemies) {
    this.enemies = enemies;
  }

  public ArrayList<Block> getBlocks() {
    return blocks;
  }

  public void setBlocks(ArrayList<Block> blocks) {
    this.blocks = blocks;
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public void setLevelNumber(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  public Finish getFinish() {
    return finish;
  }

  public void setFinish(Finish finish) {
    this.finish = finish;
  }

  public int getTimeBonus() {
    return timeBonus;
  }

  public void setTimeBonus(int timeBonus) {
    this.timeBonus = timeBonus;
  }

  public Boss getBoss() {
    return boss;
  }

  public void setBoss(Boss boss) {
    this.boss = boss;
  }

}
