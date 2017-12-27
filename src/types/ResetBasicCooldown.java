package types;

import java.util.TimerTask;

public class ResetBasicCooldown extends TimerTask {
  
  private Vegetable veggie;
  private int ability;
  
  public ResetBasicCooldown(Vegetable veggie, int ability) {
    
    this.veggie = veggie;
    this.ability = ability;
    
  }

  @Override
  public void run() {
    
    this.veggie.getAbilities()[this.ability].setAllowed(true);
    System.out.println("Ability cooldown ended.");
    
  }

}
