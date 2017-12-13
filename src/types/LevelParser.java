package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.IntStream;

import enemies.DoubleTomatoWallLevelOne;
import enemies.Knife;
import enemies.KnifeDown;
import enemies.Orange;
import enemies.Tomato;
import javafx.scene.image.Image;
import main.Constants;
import main.Main;
import solids.Brick;

public class LevelParser {
  
  public static Level parseLevel(int levelNumber) {
    
    Level level = new Level(new Image("file:resources/map/bg-" + levelNumber + ".png"));
    level.setLevelNumber(levelNumber);
    
    try {
      
      // Create a BufferedReader to read level data.
      String currentDir = new File("").getAbsolutePath();
      BufferedReader levelReader = new BufferedReader(new FileReader(currentDir + "/resources/map/level" + levelNumber + ".veggietale"));
      // Initialize list for level data.
      ArrayList<String> levelLines = new ArrayList<String>();
      
      // Read every line from file and add it to levelLines.
      try {
        String line = levelReader.readLine();
        while (line != null) {
          levelLines.add(line);
          line = levelReader.readLine();
        }
      }
      finally {
        levelReader.close();
      }
      
      ArrayList<MapItem> mapItems = level.getMapItems();
      
      for (int i = 0; i < levelLines.size(); i++) {
        // Split each line by "|" into an array.
        String[] data = levelLines.get(i).split("\\|");
        int type = Integer.parseInt(data[0]);
        Coordinates coords = new Coordinates(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        if (type == 1) {
          mapItems.add(new Brick(coords));
        }
        else if (type == 2) {
          Tomato tomato = new Tomato(coords);
          level.getMapItems().add(tomato);
          level.getEnemies().add(tomato);
        }
        else if (type == 3) {
          Knife knife = new Knife(coords);
          level.getMapItems().add(knife);
          level.getEnemies().add(knife);
        }
        else if (type == 4) {
          KnifeDown knifeDown = new KnifeDown(coords);
          level.getMapItems().add(knifeDown);
          level.getEnemies().add(knifeDown);
        }
        else if (type == 5) {
          DoubleTomatoWallLevelOne doubleTomatoWallLevelOne = new DoubleTomatoWallLevelOne(coords, new int[] {Integer.parseInt(data[3]), Integer.parseInt(data[4])});
          level.getMapItems().add(doubleTomatoWallLevelOne);
          level.getEnemies().add(doubleTomatoWallLevelOne);
        }
        else if (type == 6) {
          Orange orange = new Orange(coords, Integer.parseInt(data[3]) == 1 ? true : false, Integer.parseInt(data[4]));
          level.getMapItems().add(orange);
          level.getEnemies().add(orange);
        }
        else if (type == 0) {
          Main.getProtag().x = coords.x;
          Main.getProtag().y = coords.y;
          Main.visibleX = coords.x - 500 + (Main.getProtag().w / 2);
          Main.visibleY = coords.y - 300 + (Main.getProtag().h / 2);
        }
        if (IntStream.of(Constants.SOLIDS).anyMatch((x) -> x == type)) {
          level.getSolids().add((Solid) mapItems.get(i));
        }
        
      }
      
      for (int i = 0; i < level.getMapItems().size(); i++) {
        level.getMapItems().get(i).id = i;
      }
    
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
    return level;
    
  }

}
