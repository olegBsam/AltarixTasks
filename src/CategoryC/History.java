package CategoryC;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class History {
    private static final String pathToHistory = "history.ch";
    private static final String pathToConfig = "history.cfg";
    private int capacity = 10;
    private LinkedHashMap<String, Double> history;

    public  void setHistory(String exp, Double result){
        while (history.size() >= capacity){
            ArrayList<String> list = new ArrayList<>(history.keySet());
            history.remove(list.get(0));
        }
        history.put(exp, result);
        saveHistory();
    }

    public String getHistory(int n){
        ArrayList<String> list = new ArrayList<>(history.keySet());
        return String.valueOf(history.get(list.get(n)));
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
        writeToPath(capacity, pathToConfig);
        while (history.size() > capacity){
            ArrayList<String> list = new ArrayList<>(history.keySet());
            history.remove(list.get(0));
        }
    }

    public void loadHistory(){
        try {
            history = (LinkedHashMap<String, Double>) readFromPath(pathToHistory);
        }
        catch (IOException | ClassNotFoundException e) {
            history = new LinkedHashMap<>();
            saveHistory();
        }

        try {
            capacity = (int) readFromPath(pathToConfig);
        }
        catch (IOException | ClassNotFoundException e) {
            writeToPath(capacity, pathToConfig);
        }
    }

    public void saveHistory() {
        writeToPath(history, pathToHistory);
    }

    public void show() {
        System.out.println("История операций:");
        if (history != null){
            int i = 1;
            for (Map.Entry<String, Double> item : history.entrySet()){
                System.out.printf("%d. %s = %f%n", i++, item.getKey(), item.getValue());
            }
        }
    }

    private void writeToPath(Object obj, String path){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readFromPath(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        Object obj = new ObjectInputStream(fis).readObject();
        fis.close();
        return obj;
    }

}
