package enemies;

import main.Main;
import types.Boss;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class PineappleLaser extends Enemy {

  public PineappleLaser(Coordinates coords) {
    super(coords, "file:resources/character/pineapple/laser.png", new SolidData(500, 50, 0, 0), 0);
    this.endurance = 0;
    this.setDmg(40);
  }

  @Override
  public void enemyMovement() {
    
    final Boss BOSS = Main.getGame().getCurrentLevel().getBoss();
    this.x = BOSS.x - 480;
    this.y = BOSS.y + 175;
    
  }

}
