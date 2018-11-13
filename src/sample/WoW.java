package sample;

import java.io.*;
import java.util.Collection;

public class WoW {
    private static String wowDirectory = "";

    public static void saveToWoWRealmlist(String realmListString) {
        try (FileWriter writer = new FileWriter(wowDirectory + "\\realmlist.wtf", false)) {
            writer.write(realmListString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFromWoWRealmlist() {
        if (!wowDirectory.equals("")) {
            try (FileReader reader = new FileReader(wowDirectory + "\\realmlist.wtf")) {
                String output = "";
                int c;
                while ((c = reader.read()) != -1) {
                    output += (char) c;
                }
                return output;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getWowDirectory() {
        return wowDirectory;
    }

    public static void setWowDirectory(String wowPath) {
        wowDirectory = wowPath;
    }
}
