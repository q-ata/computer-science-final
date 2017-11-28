package types;

import java.util.TimerTask;

public class ResetProjectileCooldown extends TimerTask {
  
  private Vegetable veggie;
  
  public ResetProjectileCooldown(Vegetable veggie) {
    
    this.veggie = veggie;
    
  }

  @Override
  public void run() {
    
    veggie.setProjCooldown(false);
    
  }

}
