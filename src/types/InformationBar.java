package types;

import javafx.scene.image.Image;

public class InformationBar {
  
  private static final Image background = new Image("file:resources/infobar_background.png");
  private static final Image healthbar = new Image("file:resources/healthbar_full.png");
  private static final Image infoBg = new Image("file:resources/autism.png");
  private static final Image ammoBar = new Image("file:resources/ammo_cooldown.png");
  private static Image profile;
  private static Image charStats;

  public static Image getHealthbar() {
    return healthbar;
  }

  public static Image getInfobg() {
    return infoBg;
  }

  public static Image getAmmobar() {
    return ammoBar;
  }

  public static Image getBackground() {
    return background;
  }

  public static Image getProfile() {
    return profile;
  }

  public static void setProfile(Image profile) {
    InformationBar.profile = profile;
  }

  public static Image getCharStats() {
    return charStats;
  }

  public static void setCharStats(Image charStats) {
    InformationBar.charStats = charStats;
  }

}
