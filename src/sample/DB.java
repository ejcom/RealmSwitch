package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class DB {
    private static final ArrayList<String> realmLists = new ArrayList<>();

    public static ArrayList<String> getRealmLists() {
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

    public static void setRealmLists(ArrayList<String> list) {
        if (!list.isEmpty()) {
            realmLists.clear();
            realmLists.addAll(list);
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
