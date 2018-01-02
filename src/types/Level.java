package types;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import main.Main;
import solids.Finish;

public class Level {
  
  public int time = 0;
  public int score = 0;
  private Image background;
  private ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Block> blocks = new ArrayList<Block>();
  private int levelNumber;
  private Finish finish;
  private Timer timeTimer;
  private int timeBonus;
  
  public Level(Image background) {
    
    this.setBackground(background);
    this.timeTimer = new Timer();
    timeTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        
        Main.getGame().getCurrentLevel().time++;
        
      }
    }, 1000, 1000);
    
  }
  
  public void end(boolean success) {
    
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

}
