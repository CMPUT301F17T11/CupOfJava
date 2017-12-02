package com.cmput301f17t11.cupofjava.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by naz_t on 12/1/2017.
 */

public class BitmapImage {
    private String userName;
    private String HabitTitle;
    private String id;
    //TODO ENSURE IMAGE CORRECTLY ASSOCIATED WITH THEIR CORRESPONDING IMAGES
    private Bitmap bitmap;
    private String imageString;
    private int imageSize;
    private byte[] bitmapBytes;

    /**
     * Constructor using BitMap object
     * @param bitmap
     */
    public BitmapImage(Bitmap bitmap){
        this.bitmap = bitmap;
        processImage();
    }

    /**
     * Constructor using file path
     * @param path
     */

    public BitmapImage(String path){
        this.bitmap = BitmapFactory.decodeFile(path);
        processImage();
    }

    /**
     * Does processing of the bitmap image into a string object
     */
    private void processImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.bitmap.compress(Bitmap.CompressFormat.WEBP, 50, byteArrayOutputStream);
        this.bitmapBytes = byteArrayOutputStream.toByteArray();
        this.imageSize = this.bitmapBytes.length;
        Log.i("Image size", String.valueOf(this.imageSize)); //TODO confirm image size right
        this.imageString = Base64.encodeToString(this.bitmapBytes, Base64.NO_WRAP);

    }

    /**
     * Get the image as string so that it can be stored in ES
     * @return String
     */
    public String getImageString(){
        return this.imageString;
    }

    /**
     * Returns the compressed form of the Bitmap
     * @return
     */

    public Bitmap getCompressedImage(){
        byte[] bytes = Base64.decode(this.imageString, Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bm;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}