package enemies;

import javafx.scene.image.Image;
import main.Constants;
import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;
import types.Vegetable;
// Lemon enemy.
public class Lemon extends Enemy {

  private final Image RIGHTSPRITE = new Image("file:resources/character/lemon/lemon_sprite.png");
  private final Image LEFTSPRITE = new Image("file:resources/character/lemon/lemon_sprite_left.png");
  
  public Lemon(Coordinates coords) {
    super(coords, "file:resources/character/lemon/lemon_sprite.png", new SolidData(57, 55, 0, 0), 100);
    this.endurance = 0.9;
    this.setDmg(30);
  }

  @Override
  public void enemyMovement() {
    // Find the difference in coordinates for the enemy and the character.
    final Vegetable PROTAG = Main.getGame().getProtag();
    final int X = PROTAG.x - this.x;
    final int Y = PROTAG.y - this.y;
    // Move towards the character.
    if (X > 0) {
      this.x += 2;
      this.sprite = this.RIGHTSPRITE;
    }
    else {
      this.x -= 2;
      this.sprite = this.LEFTSPRITE;
    }
    if (Y > 0) {
      this.y += 3;
    }
    else {
      this.y -= 3;
    }
    if (Constants.SOLIDCOLLISION(this, Main.getGame().getProtag()) && Main.getGame().getProtag().hurt) {
      this.y -= 200;
      this.x -= 200;
    }
    
  }

}
