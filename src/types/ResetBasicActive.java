package types;

import java.util.TimerTask;

public class ResetBasicActive extends TimerTask {
  
  private Vegetable veggie;
  
  public ResetBasicActive(Vegetable veggie) {
    
    this.veggie = veggie;
    
  }

  @Override
  public void run() {
    
    this.veggie.basicEnd();
    this.veggie.setBasicActive(false);
    
  }

}
