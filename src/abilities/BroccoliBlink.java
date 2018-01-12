package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;

public class BroccoliBlink extends BasicAbility {
  
  private final Image sprite = new Image("file:resources/character/broccoli_sprite.png");

  public BroccoliBlink(KeyCode activator, int index) {
    super(true, 3, 240, "file:resources/icons/broccoli_blink.png", activator, index);
    this.setStacked(true);
    this.setMaxStacks(2);
    this.setCurStacks(2);
  }

  @Override
  public void doBasic() {
    
    if (this.getUser().right || (!this.getUser().left && !this.getUser().up)) {
      this.getUser().x += 75;
    }
    else if (this.getUser().left) {
      this.getUser().x -= 75;
    }
    if (this.getUser().up) {
      this.getUser().y -= 35;
      this.getUser().yVel -= 2;
    }
    Main.getGame().visibleX = this.getUser().x + (this.getUser().w / 2) - 500;
    Main.getGame().visibleY = this.getUser().y + (this.getUser().h / 2) - 300;
    
  }

  @Override
  public void basicEnd() {
    
    this.getUser().setInvincible(false);
    this.getUser().sprite = this.sprite;
    
  }

  @Override
  public void basic() {
    
    this.getUser().setInvincible(true);
    this.getUser().sprite = null;
    int[] sounds = {Sounds.BROCCOLIBLINK1, Sounds.BROCCOLIBLINK2, Sounds.BROCCOLIBLINK3, Sounds.BROCCOLIBLINK4, Sounds.BROCCOLIBLINK5};
    SoundManager.playPlayer(sounds[(int) Math.floor(Math.random() * sounds.length)]);
    
  }

}
