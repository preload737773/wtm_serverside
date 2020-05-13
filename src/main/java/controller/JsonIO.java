package main.java.controller;


import com.google.gson.Gson;
import main.java.model.Tasks;
import main.java.model.UsersWrapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonIO {
    public static void writeToJsonFile(String fileName, Object obj) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(gson.toJson(obj));
        fileWriter.close();
    }

    public static Tasks readTasksFromJsonFile(String fileName) throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(fileName);
        var read = gson.newJsonReader(fileReader);
        read.setLenient(true);
        Tasks result = gson.fromJson(read, Tasks.class);
        fileReader.close();
        return result;
    }

    public static UsersWrapper readUsersFromJsonFile(String fileName) throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(fileName);
        var read = gson.newJsonReader(fileReader);
        read.setLenient(true);
        UsersWrapper result = gson.fromJson(read, UsersWrapper.class);
        fileReader.close();
        return result;
    }
}
