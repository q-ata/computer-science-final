package types;

import main.Main;
import main.Sounds;

public class PineappleRocketAction extends BossAction {
  
  public PineappleRocketAction() {
    super(300, Sounds.PINEAPPLEROCKET);
  }

  private boolean rocketing = false;
  private boolean dir;

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
    
    Boss BOSS = Main.getGame().getCurrentLevel().getBoss();
    // Rocket towards the player if it is in place.
    if (dir) {
      BOSS.x += 19;
    }
    else {
      BOSS.x -= 19;
    }
    if (BOSS.x > 1000 || BOSS.x < -280) {
      this.rocketing = false;
    }
    
  }

  @Override
  public void end() {
    // Return back to original state.
    Main.getGame().getCurrentLevel().getBoss().sprite = Main.getGame().getCurrentLevel().getBoss().getSprites()[0];
    Main.getGame().getCurrentLevel().getBoss().w = 110;
    Main.getGame().getCurrentLevel().getBoss().h = 280;
  }

}
