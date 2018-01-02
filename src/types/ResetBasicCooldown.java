package types;

import java.util.Timer;
import java.util.TimerTask;

public class ResetBasicCooldown extends TimerTask {
  
  private Vegetable veggie;
  private int ability;
  private Timer timer;
  
  public ResetBasicCooldown(Vegetable veggie, int ability, Timer timer) {
    
    this.veggie = veggie;
    this.ability = ability;
    this.timer = timer;
    
  }

  @Override
  public void run() {
    
    this.veggie.getAbilities()[this.ability].setAllowed(true);
    this.veggie.getControls().remove(this.timer);
    
  }

}
