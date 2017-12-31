package types;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import main.Main;
import solids.Finish;

public class Level {
  
  private Image background;
  private ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Solid> solids = new ArrayList<Solid>();
  private int levelNumber;
  private Finish finish;
  private Timer timeTimer = new Timer();
  private int time = 0;
  private int score = 0;
  
  public Level(Image background) {
    
    this.setBackground(background);
    timeTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        
        Main.getCurrentLevel().setTime(Main.getCurrentLevel().getTime() + 1);
        
      }
    }, 1000, 1000);
    
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

  public ArrayList<Solid> getSolids() {
    return solids;
  }

  public void setSolids(ArrayList<Solid> solids) {
    this.solids = solids;
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

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

}
