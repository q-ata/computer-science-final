package enemies;

import main.Constants;
import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;
import types.Vegetable;
// PineappleRing enemy.
public class PineappleRing extends Enemy {
  
  public PineappleRing(Coordinates coords) {
    super(coords, "file:resources/character/pineapple/pineapple_ring.png", new SolidData(57, 55, 0, 0), 100);
    this.endurance = 0.9;
    this.setDmg(30);
  }

  @Override
  public void enemyMovement() {
    // Find the difference in coordinates for the enemy and the character.
    final Vegetable PROTAG = Main.getGame().getProtag();
    final int X = PROTAG.x - this.x;
    final int Y = PROTAG.y - this.y;
    // Move towards the character.
    if (X > 0) {
      this.x += 2;
    }
    else {
      this.x -= 2;
    }
    if (Y > 0) {
      this.y += 2;
    }
    else {
      this.y -= 2;
    }
    if (Constants.SOLIDCOLLISION(this, Main.getGame().getProtag()) && Main.getGame().getProtag().hurt) {
      this.y -= 200;
      this.x -= 200;
    }
    
  }

}
