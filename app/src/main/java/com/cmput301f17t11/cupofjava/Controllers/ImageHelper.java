package com.cmput301f17t11.cupofjava.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by naz_t on 12/2/2017.
 */

public class ImageHelper {

    /**
     * Converts a
     * @param imageString
     * @return Bitmap
     */
    public static Bitmap getImageFromString(String imageString){
        byte[] bytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bm;
    }

    /**
     * Converts a Bitmap object to String object
     * @param bitmap
     * @return String
     */
    public static String getStringFromImage(Bitmap bitmap){
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, 50, byteArrayOutputStream);
        byte[] bitmapBytes = byteArrayOutputStream.toByteArray();
        Log.i("Image size", String.valueOf(bitmapBytes.length)); //TODO confirm image size right
        str = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
        return str;
    }

    /**
     * Get string from bitmap path
     * @param path
     * @return
     */
    public static String getStringFromImage(String path){
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return getStringFromImage(bitmap);
    }


}
