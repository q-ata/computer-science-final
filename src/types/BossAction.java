package types;

public abstract class BossAction {
  
  public int length;
  public int voice;
  
  public BossAction(int length, int voice) {
    this.length = length;
    this.voice = voice;
  }
  
  public abstract void act();
  public abstract void end();

}
