package types;

import enemies.PineappleLaser;
import main.Main;
import main.Sounds;

public class PineappleLaserAction extends BossAction {
  
  private boolean spawned = false;
  private PineappleLaser laser;

  public PineappleLaserAction() {
    super(90, Sounds.PINEAPPLELASER);
  }

  @Override
  public void act() {
    // Create a laser
    final Boss BOSS = Main.getGame().getCurrentLevel().getBoss();
    if (!this.spawned) {
      BOSS.x = Main.getGame().getProtag().x + 390;
      BOSS.y = Main.getGame().getProtag().y + 100;
      this.laser = new PineappleLaser(new Coordinates(BOSS.x - 480, BOSS.y + 175));
      Main.getGame().getCurrentLevel().getEnemies().add(this.laser);
      Main.getGame().getCurrentLevel().getMapItems().add(this.laser);
      this.spawned = true;
    }
    else {
      // Keep moving upwards after.
      BOSS.y -= 8;
    }
    
  }

  @Override
  public void end() {
    // Delete the laser.
    Main.getGame().getCurrentLevel().getEnemies().remove(this.laser);
    Main.getGame().getCurrentLevel().getMapItems().remove(this.laser);
    this.spawned = false;
    
  }

}
