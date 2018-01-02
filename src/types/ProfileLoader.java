package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import main.Main;

public class ProfileLoader {
  
  public static final void loadProfile(byte profileNum) {
    
    try {
      
      String currentDir = new File("").getAbsolutePath();
      BufferedReader profileReader = new BufferedReader(new FileReader(currentDir + "/resources/save/profile" + profileNum + ".veggiedata"));
      Main.getGame().setLevelsUnlocked((byte) Integer.parseInt(profileReader.readLine()));
      profileReader.close();
      Main.getGame().setCurrentProfile(profileNum);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
