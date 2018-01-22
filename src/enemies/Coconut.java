package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class Coconut extends Enemy {

  public Coconut(Coordinates coords) {
    super(coords, "file:resources/character/pineapple/coconut.png", new SolidData(80, 86, 0, 0), 0);
    this.endurance = 0;
    this.setDmg(25);
  }

  @Override
  public void enemyMovement() {
    // Fall and delete if exceeded Y threshold.
    this.y += 9;
    if (this.y > 1000) {
      Main.getGame().getCurrentLevel().getMapItems().remove(this);
      Main.getGame().getCurrentLevel().getEnemies().remove(this);
    }
    
  }

}
