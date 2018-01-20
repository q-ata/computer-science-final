package main;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
// Sound manager for background music.
public class BackgroundMusicManager {
  
  private static final String[] tracks = {"resources/audio/tracks/home.mp3", "resources/audio/tracks/level_1.mp3", "resources/audio/tracks/level_2.mp3", "resources/audio/tracks/level_3.mp3", "resources/audio/tracks/level_4.mp3"};
  private static MediaPlayer player;
  
  public static final void setMusic(int i) {
    // Stop music if it should be stopped.
    if (i == -1) {
      if (BackgroundMusicManager.player != null) {
        BackgroundMusicManager.player.stop();
      }
      BackgroundMusicManager.player = null;
      return;
    }
    if (BackgroundMusicManager.player != null) {
      BackgroundMusicManager.player.stop();
    }
    // Play music on repeat.
    File src = new File(BackgroundMusicManager.tracks[i]);
    String path = src.toURI().toString();
    BackgroundMusicManager.player = new MediaPlayer(new Media(path));
    BackgroundMusicManager.player.setVolume(0.1);
    BackgroundMusicManager.player.setAutoPlay(true);
    BackgroundMusicManager.player.setOnEndOfMedia(() -> {
      BackgroundMusicManager.player.seek(Duration.ZERO);
    });
    
  }

}
