package types;

import java.util.TimerTask;

public class ResetBasicCooldown extends TimerTask {
  
  private Vegetable veggie;
  
  public ResetBasicCooldown(Vegetable veggie) {
    
    this.veggie = veggie;
    
  }

  @Override
  public void run() {
    
    this.veggie.setBasicAllowed(true);
    System.out.println("Ability cooldown ended.");
    
  }

}
