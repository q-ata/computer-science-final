package types;

import java.util.TimerTask;

import main.Main;

public class FpsResetter extends TimerTask {

  @Override
  public void run() {
    
    System.out.println("FPS: " + Main.getGame().getFps());
    Main.getGame().setFps((byte) 0);
    
  }

}
