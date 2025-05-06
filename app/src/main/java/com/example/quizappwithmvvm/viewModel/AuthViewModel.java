package com.example.quizappwithmvvm.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quizappwithmvvm.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private final MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private final FirebaseUser currentUser;
    private final AuthRepository repository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthRepository(application);
        currentUser = repository.getCurrentUser();
        firebaseUserMutableLiveData = repository.getFirebaseUserMutableLiveData();
    }

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void signUp(String email, String pass) {
        repository.signUp("", email, pass); // Dummy username (you can improve this)
    }

    public void signIn(String email, String pass) {
        repository.signIn(email, pass);
    }

    public void signOut() {
        repository.signOut();
    }
}
