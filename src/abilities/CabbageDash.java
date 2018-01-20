package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
import types.Block;
// Cabbage dash ability.
public class CabbageDash extends BasicAbility {
  
  private byte basicDirection;
  // Placeholder sprites.
  private final Image[] ABILITYSPRITES = {new Image("file:resources/character/cabbage/cabbage_basic_active_right.png"), new Image("file:resources/character/cabbage/cabbage_basic_active_left.png")};
  private final Image PLACEHOLDER = new Image("file:resources/character/cabbage/cabbage_sprite.png");

  public CabbageDash(KeyCode activator, int index) {
    super(true, 30, 360, "file:resources/icons/cabbage_dash.png", activator, index);
  }

  @Override
  public void doBasic() {
    // Move the character depending on direction, and check for collision with any blocks.
    this.getUser().xVel = this.basicDirection == 1 ? 10 : -10;
    for (Block block : Main.getGame().getCurrentLevel().getBlocks()) {
      if (this.getUser().xVel > 0 && block.x <= this.getUser().x + this.getUser().w + this.getUser().xVel && block.x + block.w > this.getUser().x && block.y < this.getUser().y + this.getUser().h && block.y + block.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = block.x - this.getUser().w;
        Main.getGame().visibleX = this.getUser().x - 460;
        break;
      }
      else if (block.x + block.w >= this.getUser().x + this.getUser().xVel && block.x < this.getUser().x + this.getUser().w && block.y < this.getUser().y + this.getUser().h && block.y + block.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = block.x + block.h;
        Main.getGame().visibleX = this.getUser().x - 460;
        break;
      }
    }
    this.getUser().x += this.getUser().xVel;
    Main.getGame().visibleX += this.getUser().xVel;
    this.getUser().yVel = 0;
    
  }

  @Override
  public void basicEnd() {
    // Set the sprite back and make the character vulnerable.
    this.getUser().sprite = this.PLACEHOLDER;
    this.getUser().setInvincible(false);
    
  }

  @Override
  public void basic() {
    // Make the character invincible and set the sprite.
    this.getUser().hurt = false;
    this.basicDirection = this.getUser().lastDirection;
    this.getUser().sprite = this.ABILITYSPRITES[this.basicDirection == 1 ? 0 : 1];
    this.getUser().setInvincible(true);
    SoundManager.playPlayer(Sounds.CABBAGEDASH, 0.5);
    if (this.getUser().getInvincibilityTimer() != null) {
      this.getUser().getInvincibilityTimer().cancel();
      this.getUser().getInvincibilityTimer().purge();
    }
    
  }

}
