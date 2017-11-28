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

  public Tomato(Coordinates coords) {
    
    super(coords, "file:resources/character/tomato.png", new SolidData(96, 88, 0, 0));
    this.endurance = 0;
    this.setDmg(35);
    
  }

  @Override
  public void enemyMovement() {
    
    for (Solid solid : Main.getSolids()) {
      if (Constants.SOLIDCOLLISION(this, solid)) {
        this.sprite = this.dazedSprite;
        this.endurance = 2;
        return;
      }
    }
    this.x -= 9;
    
  }

}
