package abilities;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.input.KeyCode;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
import types.Coordinates;
import types.Projectile;
import types.ProjectileData;
// Carrot machine gun ability.
public class CarrotMachineGun extends BasicAbility {
  
  private Timer gun;

  public CarrotMachineGun(KeyCode activator, int index) {
    super(false, 72, 720, "file:resources/icons/carrot_machine_gun.png", activator, index);
  }

  @Override
  public void doBasic() {
    
    return;
    
  }

  @Override
  public void basicEnd() {
    // Reset character.
    this.getUser().setSpeed(5);
    this.getUser().setProjCooldown(false);
    
    gun.cancel();
    gun.purge();
    gun = null;
    
  }

  @Override
  public void basic() {
    // Change character state.
    this.getUser().setSpeed(3);
    this.getUser().setProjCooldown(true);
    SoundManager.playPlayer(Sounds.CARROTMACHINEGUN);
    // Create a projectile every 120 milliseconds.
    gun = new Timer();
    gun.scheduleAtFixedRate(new TimerTask() {
      
      @Override
      public void run() {
        ProjectileData data = new ProjectileData(7, "file:resources/character/carrot/carrot_machine_gun", 83, 25, 0, 4);
        Projectile proj = new Projectile(new Coordinates(Main.getGame().getProtag().x, Main.getGame().getProtag().y + Math.round((Main.getGame().getProtag().h / 2) - 13)), Main.getGame().getProtag().lastDirection, data);
        Main.getGame().getCurrentLevel().getMapItems().add(proj);
        Main.getGame().getCurrentLevel().getProjectiles().add(proj);
        
      }
    }, 0, 120);
    
  }

}
