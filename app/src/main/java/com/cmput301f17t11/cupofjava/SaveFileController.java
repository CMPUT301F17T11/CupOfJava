package com.cmput301f17t11.cupofjava;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Represents a controller for data load/save using a local save file
 */

public class SaveFileController {
    private ArrayList<User> allUsers;
    private String username;
    private String saveFile = "save_file.sav";

    public SaveFileController(String user){
        this.allUsers = new ArrayList<User>();
        this.username = user;
    }

    private void loadFromFile(Context context){
        try{
            FileInputStream ifStream = context.openFileInput(saveFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ifStream));
            Gson gson = new Gson();
            Type counterArrayListType = new TypeToken<ArrayList<User>>(){}.getType();
            this.allUsers = gson.fromJson(bufferedReader, counterArrayListType);
            ifStream.close();
        }
        //create a new array list if a file does not already exist
        catch (FileNotFoundException e){
            this.allUsers = new ArrayList<User>();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    /**
     * saves current ArrayList contents in file
     * @param context
     */

    private void saveToFile(Context context){
        try{
            FileOutputStream ofStream = context.openFileOutput(saveFile, Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(ofStream));
            Gson gson = new Gson();
            gson.toJson(this.allUsers, bufferedWriter);
            bufferedWriter.flush();
            ofStream.close();
        }
        catch (FileNotFoundException e){
            //shouldn't really happen, since a file not found would create a new file.
            throw new RuntimeException("Laws of nature defied!");
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }


}
