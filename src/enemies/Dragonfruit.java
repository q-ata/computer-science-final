package enemies;

import javafx.scene.image.Image;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class Dragonfruit extends Enemy {
  
  private int moveDistance = 400;
  private boolean right;
  private int moved = 0;
  private int spikeInterval = 120;
  private int currentInterval = 0;
  private boolean spiked = false;
  private int spikeTime = 60;
  private int currentSpikeTime = 0;
  private Image inactiveSprite = new Image("file:resources/character/dragonfruit_inactive.png");
  private Image activeSprite = new Image("file:resources/character/dragonfruit_active.png");
  
  public Dragonfruit(Coordinates coords, boolean right) {
    super(coords, "file:resources/character/dragonfruit_inactive.png", new SolidData(60, 66, 0, 0), 100);
    
    this.init(right);
  }
  
  public Dragonfruit(Coordinates coords, boolean right, int moveDistance) {
    super(coords, "file:resources/character/dragonfruit_inactive.png", new SolidData(60, 66, 0, 0), 100);
    
    this.moveDistance = moveDistance;
    this.init(right);
  }
  
  private void init(boolean right) {
    this.endurance = 0.5;
    this.setDmg(30);
    this.right = right;
    if (!this.right) {
      this.moved = this.moveDistance - 8;
    }
  }

  @Override
  public void enemyMovement() {
    
    if (!this.spiked) {
      if (this.right) {
        
        this.x += 8;
        this.moved += 8;
        if (this.moved >= this.moveDistance) {
          this.right = false;
        }
        
      }
      else {
        
        this.x -= 8;
        this.moved -= 8;
        if (this.moved <= 0) {
          this.right = true;
        }
        
      }
      if (++this.currentInterval == this.spikeInterval) {
        this.currentInterval = 0;
        this.spiked = true;
        this.sprite = this.activeSprite;
        this.w = 124;
        this.h = 114;
        this.y -= 48;
        this.endurance = 1.5;
        this.setDmg(60);
      }
      return;
    }
    
    if (++this.currentSpikeTime == this.spikeTime) {
      this.currentSpikeTime = 0;
      this.spiked = false;
      this.sprite = this.inactiveSprite;
      this.w = 60;
      this.h = 66;
      this.y += 48;
      this.endurance = 0.5;
      this.setDmg(30);
    }
    
  }

}
