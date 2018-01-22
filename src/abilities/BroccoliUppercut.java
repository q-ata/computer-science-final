package abilities;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Constants;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
import types.Enemy;
// Broccoli uppercut ability.
public class BroccoliUppercut extends BasicAbility {
  
  private final Image SPRITE = new Image("file:resources/character/broccoli/broccoli_katana.png");
  private final Image PLACEHOLDER = new Image("file:resources/character/broccoli/broccoli_sprite.png");
  private ArrayList<Enemy> damaged = new ArrayList<Enemy>();

  public BroccoliUppercut(KeyCode activator, int index) {
    super(false, 55, 480, "file:resources/icons/broccoli_uppercut.png", activator, index);
  }


  @Override
  public void doBasic() {
    // Check if enemies collide with the character, if they do, deal damage.
    ArrayList<Enemy> toDelete = new ArrayList<Enemy>();
    for (Enemy enemy : Main.getGame().getCurrentLevel().getEnemies()) {
      boolean alreadyHit = false;
      for (Enemy e : damaged) {
        if (enemy.equals(e)) {
          alreadyHit = true;
        }
      }
      if (alreadyHit) {
        continue;
      }
      if (Constants.SOLIDCOLLISION(this.getUser(), enemy)) {
        double res = enemy.endurance < 1 ? 1 : enemy.endurance;
        enemy.hp -= 50 * res;
        damaged.add(enemy);
        if (enemy.hp <= 0) {
          Main.getGame().getCurrentLevel().score += enemy.getPoints();
          toDelete.add(enemy);
          Main.getGame().getCurrentLevel().getMapItems().remove(enemy);
        }
      }
    }
    for (Enemy enemy : toDelete) {
      Main.getGame().getCurrentLevel().getEnemies().remove(enemy);
    }
    if (Main.getGame().getCurrentLevel().getBoss() != null && Constants.SOLIDCOLLISION(this.getUser(), Main.getGame().getCurrentLevel().getBoss())) {
      Main.getGame().getCurrentLevel().getBoss().setHealth(Main.getGame().getCurrentLevel().getBoss().getHealth() - 50);
    }
    
  }

  @Override
  public void basicEnd() {
    // Reset the character back to basic state.
    this.getUser().sprite = this.PLACEHOLDER;
    this.getUser().setInvincible(false);
    this.getUser().w = 64;
    this.getUser().vx += 28;
    
  }

  @Override
  public void basic() {
    // Set character to new state.
    this.getUser().yVel = -30;
    this.getUser().up = true;
    this.getUser().w = 120;
    this.getUser().x -= 28;
    this.getUser().vx -= 28;
    this.getUser().sprite = this.SPRITE;
    this.getUser().setInvincible(true);
    if (this.getUser().getInvincibilityTimer() != null) {
      this.getUser().getInvincibilityTimer().cancel();
      this.getUser().getInvincibilityTimer().purge();
    }
    this.getUser().hurt = false;
    SoundManager.playPlayer(Sounds.BROCCOLIUPPERCUT);
    
  }
  
}
