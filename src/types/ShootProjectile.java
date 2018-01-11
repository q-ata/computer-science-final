package types;

import java.util.Timer;
import java.util.TimerTask;

import main.Main;

public class ShootProjectile extends TimerTask {
  
  private Vegetable veggie;
  private int count = 0;
  private int max;
  private Timer timer;
  
  public ShootProjectile(Vegetable veggie, int count, Timer timer) {
    this.veggie = veggie;
    this.max = count;
    this.timer = timer;
  }

  @Override
  public void run() {
    Projectile proj = new Projectile(new Coordinates(this.veggie.x, this.veggie.y + Math.round((this.veggie.h / 2) - (this.veggie.getProjData().h / 2))),
        this.veggie.lastDirection, this.veggie.getProjData());
    Main.getGame().getCurrentLevel().getProjectiles().add(proj);
    Main.getGame().getCurrentLevel().getMapItems().add(proj);
    if (++this.count == this.max) {
      this.timer.cancel();
      this.timer.purge();
    }
  }

}
