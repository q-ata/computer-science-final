package enemies;

import javafx.scene.image.Image;
import main.Constants;
import main.Main;
import types.Coordinates;
import types.Enemy;
import types.Solid;
import types.SolidData;

public class Tomato extends Enemy {
  
  private Image dazedSprite = new Image("file:resources/character/tomato_dazed.png");
  private int time = 0;
  private boolean dazed = false;

  public Tomato(Coordinates coords) {
    
    super(coords, "file:resources/character/tomato.png", new SolidData(96, 88, 0, 0), 50);
    this.endurance = 0;
    this.setDmg(35);
    
  }

  @Override
  public void enemyMovement() {
    
    if (this.dazed) {
      return;
    }
    
    if (time > 0) {
      
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
    
    for (Solid solid : Main.getCurrentLevel().getSolids()) {
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
