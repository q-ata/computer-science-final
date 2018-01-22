package types;

import javafx.scene.image.Image;

public abstract class Boss extends Solid {
  
  private BossAction[] actions;
  private BossBehaviour behaviour;
  private Image[] sprites;
  private int health;
  private int maxHealth;
  private boolean acting = false;
  private int curAction;
  private int dmg = 30;

  public Boss(Coordinates coords, String[] sprites, BossAction[] actions, int health, SolidData solidData) {
    super(coords, sprites[0], solidData);
    this.setActions(actions);
    this.setBehaviour(behaviour);
    Image[] spriteImages = new Image[sprites.length];
    for (int i = 0; i < sprites.length; i++) {
      spriteImages[i] = new Image(sprites[i]);
    }
    this.setSprites(spriteImages);
    this.setHealth(health);
    this.setMaxHealth(health);
  }

  public BossAction[] getActions() {
    return actions;
  }

  public void setActions(BossAction[] actions) {
    this.actions = actions;
  }

  public BossBehaviour getBehaviour() {
    return behaviour;
  }

  public void setBehaviour(BossBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  public Image[] getSprites() {
    return sprites;
  }

  public void setSprites(Image[] sprites) {
    this.sprites = sprites;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public boolean isActing() {
    return acting;
  }

  public void setActing(boolean acting) {
    this.acting = acting;
  }

  public int getCurAction() {
    return curAction;
  }

  public void setCurAction(int curAction) {
    this.curAction = curAction;
  }

  public int getDmg() {
    return dmg;
  }

  public void setDmg(int dmg) {
    this.dmg = dmg;
  }

}
