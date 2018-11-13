package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class SaveLoadFile {
    private static ArrayList<Object> preferences = new ArrayList<>();
    static {
        preferences.add(0, new ArrayList<String>());
        preferences.add(1, new String(""));
        preferences.add(2, 0);
    }
    // Indexes
    // 0 - DB
    // 1 - wowPath
    // 2 - GUI save


    public static boolean save() {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("preferences.out");
            oos = new ObjectOutputStream(fos);
            preferences.set(0, DB.getRealmLists());
            preferences.set(1, WoW.getWowDirectory());
            preferences.set(2, Controller.getSelectionItem());
            oos.writeObject(preferences);
            oos.flush();
            oos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean load() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("preferences.out");
            ois = new ObjectInputStream(fis);
            preferences = (ArrayList<Object>) ois.readObject();
            DB.setRealmLists((ArrayList<String>) preferences.get(0));
            WoW.setWowDirectory((String) preferences.get(1));
            Controller.setSelectionItem((int) preferences.get(2));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }
}
