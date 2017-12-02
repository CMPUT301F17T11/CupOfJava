package com.cmput301f17t11.cupofjava.Controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.cmput301f17t11.cupofjava.Models.BitmapImage;
import com.searchly.jestdroid.JestDroidClient;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;

/**
 * Created by naz_t on 12/1/2017.
 */

public class ElasticsearchImageHandler {
    private static JestDroidClient client;

    public static class AddImageTask extends AsyncTask<BitmapImage, Void, Void>{
        @Override
        protected Void doInBackground(BitmapImage... images){
            for (BitmapImage bitmapImage : images){
                Index index = new Index.Builder(bitmapImage)
                        .index("cmput301f17t11_cupofjava").type("image").build();

                try{
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()){
                        bitmapImage.setId(result.getId());
                        Log.i("AddImageTask", "Added new image to ES");
                    }
                    else{
                        Log.i("AddImageTask Error", "Failed to add image to ES ");
                    }
                }
                catch (Exception e){
                    Log.i("Error", "Failed adding BitmapImage to ES");
                }

            }
            return null;
        }
    }

    //TODO: Get image task
}
