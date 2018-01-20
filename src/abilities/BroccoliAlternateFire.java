package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import types.BasicAbility;
import types.ProjectileData;
// Alternate fire ability for broccoli. Snipe/Burst shot.
public class BroccoliAlternateFire extends BasicAbility {
  // Primary and secondary icons.
  private final Image primary = new Image("file:resources/icons/broccoli_alternate_fire.png");
  private final Image secondary = new Image("file:resources/icons/broccoli_alternate_fire_2.png");
  
  private boolean alternate = false;

  public BroccoliAlternateFire(KeyCode activator, int index) {
    super(false, 1, 1, "file:resources/icons/broccoli_alternate_fire.png", activator, index);
    this.setReinstance(true);
  }

  @Override
  public void doBasic() {
    
    return;
    
  }
  
  @Override
  public void basicEnd() {
    // If the user is dead, reset to primary fire.
    if (this.getUser().hp <= 0) {
      this.alternate = true;
      this.basic();
    }
    
  }

  @Override
  public void basic() {
    // Change the fire mode then set the icon.
    this.alternate = !this.alternate;
    if (this.alternate) {
      this.getUser().setProjData(new ProjectileData(12, "file:resources/character/broccoli/shuriken_projectile", 30, 30, 350, 9, 3, 65));
      this.setIcon(this.secondary);
    }
    else {
      this.getUser().setProjData(new ProjectileData(18, "file:resources/character/broccoli/shuriken_projectile", 30, 30, 750, 60));
      this.setIcon(this.primary);
    }
    
  }

}
