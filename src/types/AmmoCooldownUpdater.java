package types;

import java.util.Timer;
import java.util.TimerTask;

public class AmmoCooldownUpdater extends TimerTask {
  
  private Vegetable veggie;
  private Timer timer;
  
  public AmmoCooldownUpdater(Vegetable veggie, Timer timer) {
    
    this.veggie = veggie;
    this.timer = timer;
    
  }

  @Override
  public void run() {
    // Increase the visual amount of time remaining before being able to shoot again.
    veggie.setProjCooldownFraction(veggie.getProjCooldownFraction() + 1);
    if (veggie.getProjCooldownFraction() == 10) {
      this.timer.cancel();
      this.timer.purge();
    }
    
  }

}
