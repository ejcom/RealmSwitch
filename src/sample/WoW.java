package sample;

import java.io.*;
import java.util.Collection;

public class WoW {
    private static String wowDirectory = "";

    public static void exportRealmList(String realmListString) {
        try (FileWriter writer = new FileWriter(wowDirectory + "\\realmlist.wtf", false)) {
            writer.write(realmListString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String importRealmList() {
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
    }

    public static String getWowDirectory() {
        return wowDirectory;
    }

    public static void setWowDirectory(String wowDirectory) {
        WoW.wowDirectory = wowDirectory;
    }

    public static void saveWowDirectory() {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("wowPath.out");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(WoW.wowDirectory);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadWowDirectory() {
        if (wowDirectory.equals("")) {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                fis = new FileInputStream("wowPath.out");
                ois = new ObjectInputStream(fis);
                WoW.wowDirectory = (String) ois.readObject();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
