package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class DB {
    private static final ArrayList<String> realmLists = new ArrayList<>();


    public static boolean saveDB() {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("db.out");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(realmLists);
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

    public static boolean loadDB() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("db.out");
            ois = new ObjectInputStream(fis);
            realmLists.addAll((Collection<? extends String>) ois.readObject());
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

    public ArrayList<String> getRealmLists() {
        return realmLists;
    }

    public boolean addRealmList(String value) {
        if (realmLists.contains(value)) {
            return false;
        } else {
            realmLists.add(value);
            return true;
        }
    }

    public boolean removeRealmList(String value) {
        try {
            realmLists.remove(value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
