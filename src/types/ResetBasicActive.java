package types;

import java.util.Timer;
import java.util.TimerTask;

public class ResetBasicActive extends TimerTask {
  
  private Vegetable veggie;
  private int ability;
  private Timer timer;
  
  public ResetBasicActive(Vegetable veggie, int ability, Timer timer) {
    
    this.veggie = veggie;
    this.ability = ability;
    this.timer = timer;
    
  }

  @Override
  public void run() {
    
    this.veggie.getAbilities()[this.ability].basicEnd();
    this.veggie.getAbilities()[this.ability].setActive(false);
    this.veggie.getControls().remove(timer);
    
  }

}
