package enemies;

import javafx.scene.image.Image;
import main.Constants;
import main.Main;
import types.Block;
import types.Coordinates;
import types.Enemy;
import types.SolidData;
// Tomato enemy.
public class Tomato extends Enemy {
  
  private Image dazedSprite = new Image("file:resources/character/tomato/tomato_dazed.png");
  private int time = 0;
  private boolean dazed = false;

  public Tomato(Coordinates coords) {
    
    super(coords, "file:resources/character/tomato/tomato.png", new SolidData(96, 88, 0, 0), 50);
    this.endurance = 0;
    this.setDmg(35);
    
  }

  @Override
  public void enemyMovement() {
    
    if (this.dazed) {
      return;
    }
    
    if (time > 0) {
      // Move while there is still time on its timer.
      this.setTime(this.getTime() - 1);
      if (this.getTime() == 0) {
        this.sprite = this.dazedSprite;
        this.endurance = 2;
        this.dazed = true;
        return;
      }
      this.x -= 9;
      return;
      
    }
    // Stop if collision with a solid.
    for (Block solid : Main.getGame().getCurrentLevel().getBlocks()) {
      if (Constants.SOLIDCOLLISION(this, solid)) {
        this.sprite = this.dazedSprite;
        this.endurance = 2;
        this.dazed = true;
        return;
      }
    }
    this.x -= 9;
    
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

}
