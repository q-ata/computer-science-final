package types;
 
import enemies.Coconut;
import main.Main;
import main.Sounds;
 
public class PineappleCoconutAction extends BossAction {
  
  private int tick = 0;
  private int pause = 0;
 
  public PineappleCoconutAction() {
    super(305, Sounds.PINEAPPLECOCONUT);
  }
 
  @Override
  public void act() {
    if (++this.pause < 5) {
      return;
    }
    // Generate a coconut enemy every 50 ticks.
    if (this.tick++ % 50 == 0) {
      Coconut coconut = new Coconut(new Coordinates(Main.getGame().getProtag().x + (Main.getGame().getProtag().lastDirection == 1 ? 40 : -40), Main.getGame().getProtag().y - 300));
      Main.getGame().getCurrentLevel().getEnemies().add(coconut);
      Main.getGame().getCurrentLevel().getMapItems().add(coconut);
    }
    
  }
 
  @Override
  public void end() {
  
    this.pause = 0;
    
  }
 
}