package types;

import javafx.scene.image.Image;

public class InformationBar {
  
  private static final Image healthbar = new Image("file:resources/healthbar_full.png");
  private static final Image infoBg = new Image("file:resources/autism.png");
  private static final Image ammoBar = new Image("file:resources/ammo_cooldown.png");

  public static Image getHealthbar() {
    return healthbar;
  }

  public static Image getInfobg() {
    return infoBg;
  }

  public static Image getAmmobar() {
    return ammoBar;
  }

}
