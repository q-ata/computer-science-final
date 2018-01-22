package types;

import enemies.PineappleRing;
import main.Main;
import main.Sounds;

public class PineappleRingsAction extends BossAction {
  
  private int tick = 0;

  public PineappleRingsAction() {
    super(180, Sounds.PINEAPPLERINGS);
  }

  @Override
  public void act() {
    // Generate a pineapple ring enemy every 60 ticks.
    if (this.tick++ % 60 == 0) {
      PineappleRing ring = new PineappleRing(new Coordinates(Main.getGame().getCurrentLevel().getBoss().x, Main.getGame().getCurrentLevel().getBoss().y));
      Main.getGame().getCurrentLevel().getEnemies().add(ring);
      Main.getGame().getCurrentLevel().getMapItems().add(ring);
    }
  }

  @Override
  public void end() {
    // Restore some health.
    Main.getGame().getCurrentLevel().getBoss().setHealth(Main.getGame().getCurrentLevel().getBoss().getHealth() + 250);
    return;
    
  }

}
