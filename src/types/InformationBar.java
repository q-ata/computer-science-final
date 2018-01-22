package types;

import javafx.scene.image.Image;

public class InformationBar {
  // Images to be shown for the information bar at the bottom.
  private static final Image BACKGROUND = new Image("file:resources/infobar_background.png");
  private static final Image HEALTHBAR = new Image("file:resources/healthbar_full.png");
  private static final Image INFOBG = new Image("file:resources/autism.png");
  private static final Image AMMOBAR = new Image("file:resources/ammo_cooldown.png");
  private static Image profile;
  private static Image charStats;

  public static Image getHealthbar() {
    return HEALTHBAR;
  }

  public static Image getInfobg() {
    return INFOBG;
  }

  public static Image getAmmobar() {
    return AMMOBAR;
  }

  public static Image getBackground() {
    return BACKGROUND;
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
