package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
// Carrot life leech ability.
public class CarrotLifeLeech extends BasicAbility {
  // New sprites to signify life steal mode.
  private final Image[] NEWSPRITES = {new Image("file:resources/character/carrot/carrot_healing_sprite.png"), new Image("file:resources/character/carrot/carrot_healing_sprite_left.png"),
      new Image("file:resources/character/carrot/carrot_invincible_healing_sprite.png"), new Image("file:resources/character/carrot/carrot_invincible_healing_sprite_left.png")};
  private Image[] placeholders;

  public CarrotLifeLeech(KeyCode activator, int index) {
    super(false, 240, 900, "file:resources/icons/carrot_life_leech.png", activator, index);
  }

  @Override
  public void doBasic() {
    
  }

  @Override
  public void basicEnd() {
    // Reset back to original state.
    this.getUser().lifesteal = 0.0;
    this.getUser().sprite = this.placeholders[0];
    this.getUser().setSpriteLeft(this.placeholders[1]);
    this.getUser().setHurtSprite(this.placeholders[2]);
    this.getUser().setHurtLeft(this.placeholders[3]);
    
  }

  @Override
  public void basic() {
    // Set life steal and change character spritesheet.
    this.getUser().lifesteal = 0.12;
    this.placeholders = new Image[] {this.getUser().sprite, this.getUser().getSpriteLeft(), this.getUser().getHurtSprite(), this.getUser().getHurtLeft()};
    this.getUser().sprite = this.NEWSPRITES[0];
    this.getUser().setSpriteLeft(this.NEWSPRITES[1]);
    this.getUser().setHurtSprite(this.NEWSPRITES[2]);
    this.getUser().setHurtLeft(this.NEWSPRITES[3]);
    SoundManager.playPlayer(Sounds.CARROTLIFELEECH);
    
  }

}
