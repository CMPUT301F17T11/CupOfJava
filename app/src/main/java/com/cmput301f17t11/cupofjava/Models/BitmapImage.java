package com.cmput301f17t11.cupofjava.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by naz_t on 12/1/2017.
 */

public class BitmapImage {
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
     * Does processing of the bitmap image
     */
    private void processImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.bitmap.compress(Bitmap.CompressFormat.WEBP, 50, byteArrayOutputStream);
        this.bitmapBytes = byteArrayOutputStream.toByteArray();
        Log.i("Image size", String.valueOf(bitmapBytes.length));
    }
}