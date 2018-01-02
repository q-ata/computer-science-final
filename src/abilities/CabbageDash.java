package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
import types.Block;

public class CabbageDash extends BasicAbility {
  
  private byte basicDirection;
  private final Image[] basicAbilitySprites = {new Image("file:resources/character/cabbage_basic_active_right.png"), new Image("file:resources/character/cabbage_basic_active_left.png")};
  private final Image placeholderSprite = new Image("file:resources/character/cabbage_sprite.png");

  public CabbageDash(KeyCode activator, int index) {
    super(true, 500, 6000, "file:resources/icons/cabbage_dash.png", activator, index);
  }

  @Override
  public void doBasic() {
    
    this.getUser().xVel = this.basicDirection == 1 ? 10 : -10;
    for (Block block : Main.getCurrentLevel().getBlocks()) {
      if (this.getUser().xVel > 0 && block.x <= this.getUser().x + this.getUser().w + this.getUser().xVel && block.x + block.w > this.getUser().x && block.y < this.getUser().y + this.getUser().h && block.y + block.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = block.x - this.getUser().w;
        Main.visibleX = this.getUser().x - 460;
        break;
      }
      else if (block.x + block.w >= this.getUser().x + this.getUser().xVel && block.x < this.getUser().x + this.getUser().w && block.y < this.getUser().y + this.getUser().h && block.y + block.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = block.x + block.h;
        Main.visibleX = this.getUser().x - 460;
        break;
      }
    }
    this.getUser().x += this.getUser().xVel;
    Main.visibleX += this.getUser().xVel;
    this.getUser().yVel = 0;
    
  }

  @Override
  public void basicEnd() {
    
    this.getUser().sprite = this.placeholderSprite;
    this.getUser().setInvincible(false);
    
  }

  @Override
  public void basic() {
    
    this.getUser().hurt = false;
    this.basicDirection = this.getUser().lastDirection;
    this.getUser().sprite = this.basicAbilitySprites[this.basicDirection == 1 ? 0 : 1];
    this.getUser().setInvincible(true);
    SoundManager.playPlayer(Sounds.CABBAGEDASH, 0.5);
    if (this.getUser().getInvincibilityTimer() != null) {
      this.getUser().getInvincibilityTimer().cancel();
      this.getUser().getInvincibilityTimer().purge();
    }
    
  }

}
