package types;

import main.Main;
import main.Sounds;

public class PineappleRocketAction extends BossAction {

  private boolean rocketing = false;
  private boolean dir;
  private int rocketTimeout = 0;
  
  public PineappleRocketAction() {
    super(315, Sounds.PINEAPPLEROCKET);
  }
  
  @Override
  public void act() {
    // If rocketing, ignore and keep rocketing.
    if (this.rocketing) {
      this.rocket(this.dir);
      return;
    }
    // Start moving into position to rocket at the character.
    final Vegetable PROTAG = Main.getGame().getProtag();
    final int Y = PROTAG.y - Main.getGame().getCurrentLevel().getBoss().y;
    final Boss BOSS = Main.getGame().getCurrentLevel().getBoss();
    // Start rocketing if the pineapple is in place.
    if (Y > -30 && Y < 30) {
      this.dir = PROTAG.x > BOSS.x;
      this.rocket(this.dir);
      this.rocketing = true;
      BOSS.sprite = BOSS.getSprites()[this.dir ? 1 : 2];
      BOSS.w = 278;
      BOSS.h = 110;
      return;
    }
    // Move towards the player if it is too far from the player.
    if (Y > 0) {
      BOSS.y += 8;
    }
    else {
      BOSS.y -= 8;
    }
    
  }
  
  private void rocket(boolean dir) {
    
    if (++this.rocketTimeout < 15) {
      return;
    }
    
    Boss BOSS = Main.getGame().getCurrentLevel().getBoss();
    // Rocket towards the player if it is in place.
    if (dir) {
      BOSS.x += 19;
    }
    else {
      BOSS.x -= 19;
    }
    if (BOSS.x > Main.getGame().getProtag().x + Main.getGame().getProtag().w + 450 || BOSS.x + BOSS.w < Main.getGame().getProtag().x - 450) {
      this.rocketing = false;
    }
    
  }

  @Override
  public void end() {
    // Return back to original state.
    Main.getGame().getCurrentLevel().getBoss().sprite = Main.getGame().getCurrentLevel().getBoss().getSprites()[0];
    Main.getGame().getCurrentLevel().getBoss().w = 110;
    Main.getGame().getCurrentLevel().getBoss().h = 280;
    this.rocketTimeout = 0;
  }

}
