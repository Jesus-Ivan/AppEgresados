package com.example.pruebarecyclerview.providers;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageProvider {
    //Variables
    StorageReference mStorage;
    public ImageProvider(){
        mStorage= FirebaseStorage.getInstance().getReference();
    }


}
