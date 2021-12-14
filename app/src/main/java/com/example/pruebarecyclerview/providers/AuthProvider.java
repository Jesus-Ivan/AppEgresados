package com.example.pruebarecyclerview.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {
    //Variables globales
    private FirebaseAuth mAuth;

    public AuthProvider(){
        mAuth= FirebaseAuth.getInstance();
    }
    public Task<AuthResult> register(String  email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> login(String email, String password){
       return mAuth.signInWithEmailAndPassword(email,password);
    }

    public String getUid(){
        if(mAuth.getCurrentUser()!=null){
            return mAuth.getCurrentUser().getUid();
        }else {
            return null;
        }
    }

    public String getEmail(){
        if(mAuth.getCurrentUser()!=null){
            return mAuth.getCurrentUser().getEmail();
        }else {
            return null;
        }
    }
}


