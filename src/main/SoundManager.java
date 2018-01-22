package main;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class SoundManager {
  
  private static final String[] audioPaths = {"resources/audio/effect/shooting.mp3", "resources/audio/effect/jump.mp3", "resources/audio/effect/cabbage_dash.mp3", "resources/audio/effect/carrot_machine_gun.mp3",
      "resources/audio/effect/ability_cooldown.mp3", "resources/audio/effect/carrot_life_leech.mp3", "resources/audio/effect/broccoli_blink_1.mp3", "resources/audio/effect/broccoli_blink_2.mp3",
      "resources/audio/effect/broccoli_blink_3.mp3", "resources/audio/effect/broccoli_blink_4.mp3", "resources/audio/effect/broccoli_blink_5.mp3", "resources/audio/effect/broccoli_uppercut.mp3",
      "resources/audio/effect/push_forward.mp3", "resources/audio/effect/death_comes.mp3", "resources/audio/effect/pineapple_ring.mp3", "resources/audio/effect/coconut.mp3"};
  private static MediaPlayer[] audioPlayers;
  
  
  public SoundManager() {
    
    SoundManager.audioPlayers = new MediaPlayer[SoundManager.audioPaths.length];
    for (int i = 0; i < SoundManager.audioPlayers.length; i++) {
      File src = new File(SoundManager.audioPaths[i]);
      String path = src.toURI().toString();
      SoundManager.audioPlayers[i] = new MediaPlayer(new Media(path));
    }
    
  }
  
  private static void play(int i, double v) {
    
    if (SoundManager.audioPlayers[i].getStatus().equals(Status.PLAYING)) {
      File src = new File(SoundManager.audioPaths[i]);
      String path = src.toURI().toString();
      SoundManager.audioPlayers[i] = new MediaPlayer(new Media(path));
    }
    SoundManager.audioPlayers[i].setVolume(v);
    SoundManager.audioPlayers[i].play();
    SoundManager.audioPlayers[i].setOnEndOfMedia(() -> {
      File src = new File(SoundManager.audioPaths[i]);
      String path = src.toURI().toString();
      SoundManager.audioPlayers[i] = new MediaPlayer(new Media(path));
    });
    
  }
  
  public static void playPlayer(int i) {
    
    SoundManager.play(i, 1);
    
  }
  
  public static void playPlayer(int i, double v) {
    
    SoundManager.play(i,  v);
    
  }

}
