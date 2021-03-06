package types;

import main.Main;
import main.SoundManager;

public class PineappleBehaviour extends BossBehaviour {
  
  private Boss boss;
  private int actLength;
  private int maxActLength;
  private int doActionTime = 0;
  private int doActionInterval = 500;
  private int state = 0;
  private int stateTime;
  private boolean arcRight;
  private boolean arcUp = true;
  private int arcSpeed = 11;
  private int arcThreshold;
  private Coordinates[] spiral;
  private int curSpiral = 0;
  private int spiralConstant;
  
  private void stay() {
    // Option to stay still.
    if (++this.stateTime == 120) {
      this.state = 0;
      this.stateTime = 0;
    }
  }
  
  private void moveArc() {
    // If arcing upwards.
    if (this.arcUp) {
      this.getBoss().x += this.arcRight ? 1 : -1;
      this.getBoss().y -= this.arcSpeed;
      if (this.getBoss().y < this.arcThreshold) {
        this.arcUp = false;
      }
    }
    // If arcing downwards.
    else {
      this.getBoss().x += this.arcRight ? 1 : -1;
      this.getBoss().y += this.arcSpeed;
      if (this.getBoss().y > this.arcThreshold + 600) {
        this.arcUp = true;
        this.state = 0;
      }
    }
  }
  
  private Coordinates[] generateSpiral() {
    
    this.spiralConstant = (int) Math.floor(Math.random() * 125) + 25;
    Coordinates[] coords = new Coordinates[270];
    for (int counter = 0; counter < 270; counter++) {
      double angle = 0.1 * counter;
      int x = (int) (this.getBoss().x + ((this.spiralConstant + (10 * angle)) * Math.cos(angle)));
      int y = (int) (this.getBoss().y + ((this.spiralConstant + (10 * angle)) * Math.sin(angle)));
      coords[counter] = new Coordinates(x, y);
    }
    return coords;
    
  }
  
  private void spiral() {
    
    if (this.spiral == null) {
      this.spiral = this.generateSpiral();
    }
    this.getBoss().x = this.spiral[this.curSpiral].x;
    this.getBoss().y = this.spiral[this.curSpiral++].y;
    if (this.curSpiral == 269) {
      this.curSpiral = 0;
      this.spiral = null;
      this.state = 0;
    }
    
  }
  
  private void regularAction() {
    switch (this.state) {
    // If time to decide on a new movement.
    case 0:
      if (this.getBoss().x > 1200 || this.getBoss().x < -300 || this.getBoss().y < -200 || this.getBoss().y > 800) {
        this.getBoss().x = 600;
        this.getBoss().y = 80;
      }
      // Decide on a new action depending on current pineapple health.
      final double RAND = Math.random();
      if (RAND < (this.getBoss().getHealth() * 3 < this.getBoss().getMaxHealth() ? 0.2 : 0.4)) {
        this.state = 1;
      }
      else if (RAND < 0.8) {
        this.state = 2;
        this.arcRight = this.getBoss().x < 500;
        this.arcThreshold = Main.getGame().getProtag().y - 500;
      }
      else {
        this.state = 3;
      }
      break;
      // Move dependant on current state.
    case 1:
      this.stay();
      break;
    case 2:
      this.moveArc();
      break;
    case 3:
      this.spiral();
      break;
    }
  }
  // Act if pineapple is high health.
  @Override
  public void normal() {
    
    if (this.getBoss().isActing()) {
      this.ability();
      return;
    }
    // If time to act, do so.
    if (++this.doActionTime >= this.doActionInterval) { 
      this.getBoss().setActing(true);
      this.getBoss().setCurAction((int) Math.floor(Math.random() * this.getBoss().getActions().length));
      this.maxActLength = this.getBoss().getActions()[this.getBoss().getCurAction()].length;
      SoundManager.playPlayer(this.getBoss().getActions()[this.getBoss().getCurAction()].voice);
      return;
    }
    
    this.regularAction();
    
  }

  @Override
  public void ability() {
    // Stop acting an ability if time is up.
    if (++this.actLength >= this.maxActLength) {
      this.getBoss().setActing(false);
      this.actLength = 0;
      this.doActionTime = 0;
      this.getBoss().getActions()[this.getBoss().getCurAction()].end();
      return;
    }
    // Otherwise, continue the action.
    this.getBoss().getActions()[this.getBoss().getCurAction()].act();
    
  }
  // Act only if the pineapple is low on health.
  @Override
  public void rage() {
    
    if (this.getBoss().isActing()) {
      this.ability();
      return;
    }
    // Use abilities faster.
    this.doActionTime += 2;
    if (this.doActionTime == this.doActionInterval) {
      this.getBoss().setActing(true);
      this.getBoss().setCurAction((int) Math.floor(Math.random() * this.getBoss().getActions().length));
      this.maxActLength = this.getBoss().getActions()[this.getBoss().getCurAction()].length;
      SoundManager.playPlayer(this.getBoss().getActions()[this.getBoss().getCurAction()].voice);
      return;
    }
    
    this.arcSpeed = 16;
    this.regularAction();
    
  }

  public Boss getBoss() {
    return boss;
  }

  public void setBoss(Boss boss) {
    this.boss = boss;
  }

}
