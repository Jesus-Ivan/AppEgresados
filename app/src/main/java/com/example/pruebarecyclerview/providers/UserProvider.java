package com.example.pruebarecyclerview.providers;

import com.example.pruebarecyclerview.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProvider {
    private CollectionReference mCollection;

    public UserProvider(){
        mCollection= FirebaseFirestore.getInstance().collection("Users");
    }

    public Task<DocumentSnapshot> getUser(String id){
        return mCollection.document(id).get();
    }

    public Task<Void> create(User user){
        return mCollection.document(user.getId()).set(user);
    }
}
