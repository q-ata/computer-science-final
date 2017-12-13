package types;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Level {
  
  private Image background;
  private ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Solid> solids = new ArrayList<Solid>();
  private int levelNumber;
  
  public Level(Image background) {
    
    this.setBackground(background);
    
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

}
