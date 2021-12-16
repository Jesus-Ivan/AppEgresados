package com.example.pruebarecyclerview.providers;

import android.content.Context;

import com.example.pruebarecyclerview.utils.CompressorBitmapImage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.util.Date;

public class ImageProvider {
    //Variables
    StorageReference mStorage;
    public ImageProvider(){
        mStorage= FirebaseStorage.getInstance().getReference();
    }

    public UploadTask save(Context context, File file){
        byte[] imageByte = CompressorBitmapImage.getImg(context,file.getPath(),900,900);
        StorageReference storage = mStorage.child(new Date()+".jpg");
        mStorage= storage;
        UploadTask task = storage.putBytes(imageByte);
        return  task;
    }
    public StorageReference getStorage(){
        return mStorage;
    }


}
