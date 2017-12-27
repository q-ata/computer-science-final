package types;

import java.util.TimerTask;

public class ResetBasicActive extends TimerTask {
  
  private Vegetable veggie;
  private int ability;
  
  public ResetBasicActive(Vegetable veggie, int ability) {
    
    this.veggie = veggie;
    this.ability = ability;
    
  }

  @Override
  public void run() {
    
    this.veggie.getAbilities()[this.ability].basicEnd();
    this.veggie.getAbilities()[this.ability].setActive(false);
    
  }

}
