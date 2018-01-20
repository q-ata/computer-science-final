package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.IntStream;

import enemies.*;
import javafx.scene.image.Image;
import main.Constants;
import main.Main;
import solids.*;

public class LevelParser {
  
  public static Level parseLevel(int levelNumber) {
    
    Level level = new Level(new Image("file:resources/map/bg-" + levelNumber + ".jpg"));
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
      
      String[] levelData = levelLines.get(0).split("\\|");
      levelLines.remove(0);
      level.setTimeBonus(Integer.parseInt(levelData[0]));
      
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
          if (data.length > 3) {
            tomato.setTime(Integer.parseInt(data[3]));
          }
          mapItems.add(tomato);
          level.getEnemies().add(tomato);
        }
        else if (type == 3) {
          Knife knife = new Knife(coords);
          mapItems.add(knife);
          level.getEnemies().add(knife);
        }
        else if (type == 4) {
          KnifeDown knifeDown = new KnifeDown(coords);
          mapItems.add(knifeDown);
          level.getEnemies().add(knifeDown);
        }
        else if (type == 5) {
          DoubleTomatoWallLevelOne doubleTomatoWallLevelOne = new DoubleTomatoWallLevelOne(coords, new int[] {Integer.parseInt(data[3]), Integer.parseInt(data[4])});
          mapItems.add(doubleTomatoWallLevelOne);
          level.getEnemies().add(doubleTomatoWallLevelOne);
        }
        else if (type == 6) {
          Orange orange = new Orange(coords, Integer.parseInt(data[3]) == 1 ? true : false, Integer.parseInt(data[4]));
          if (data.length > 5) {
            orange.setSeedX(Integer.parseInt(data[5]));
            orange.setSeedY(Integer.parseInt(data[6]));
          }
          mapItems.add(orange);
          level.getEnemies().add(orange);
        }
        else if (type == 7) {
          Portal portal = new Portal(coords, new Coordinates(Integer.parseInt(data[3]), Integer.parseInt(data[4])));
          mapItems.add(portal);
        }
        else if (type == 8) {
          Eggplant eggplant;
          if (data.length > 3) {
            eggplant = new Eggplant(coords, Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
          }
          else {
            eggplant = new Eggplant(coords);
          }
          mapItems.add(eggplant);
          level.getEnemies().add(eggplant);
        }
        else if (type == 9) {
          Dragonfruit dragonfruit;
          if (data.length > 4) {
            dragonfruit = new Dragonfruit(coords, Integer.parseInt(data[3]) == 1, Integer.parseInt(data[4]));
          }
          else {
            dragonfruit = new Dragonfruit(coords, Integer.parseInt(data[3]) == 1);
          }
          mapItems.add(dragonfruit);
          level.getEnemies().add(dragonfruit);
        }
        else if (type == 10) {
          Lemon lemon = new Lemon(coords);
          mapItems.add(lemon);
          level.getEnemies().add(lemon);
        }
        else if (type == 1337) {
          Finish finish = new Finish(coords);
          mapItems.add(finish);
          level.setFinish(finish);
        }
        else if (type == 0) {
          Main.getGame().getProtag().x = coords.x;
          Main.getGame().getProtag().y = coords.y;
          Main.getGame().visibleX = coords.x - 500 + (Main.getGame().getProtag().w / 2);
          Main.getGame().visibleY = coords.y - 300 + (Main.getGame().getProtag().h / 2);
        }
        if (IntStream.of(Constants.BLOCKS).anyMatch((x) -> x == type)) {
          level.getBlocks().add((Block) mapItems.get(i));
        }
        
      }
      
      for (int i = 0; i < mapItems.size(); i++) {
        mapItems.get(i).id = i;
      }
    
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
    return level;
    
  }

}
