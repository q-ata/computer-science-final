package types;

import java.util.TimerTask;

public class ReverseInvincibility extends TimerTask {
  
  private Vegetable protag;
  
  public ReverseInvincibility(Vegetable protag) {
    
    this.protag = protag;
    
  }

  @Override
  public void run() {
    
    this.protag.setInvincible(false);
    this.protag.hurt = false;
    
  }

}
