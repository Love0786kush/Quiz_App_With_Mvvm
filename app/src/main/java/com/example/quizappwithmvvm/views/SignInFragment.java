package com.example.quizappwithmvvm.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappwithmvvm.R;
import com.example.quizappwithmvvm.viewModel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

    private AuthViewModel viewModel;
    private NavController navController;

    private EditText editEmail;
    private EditText editPassword;
    private TextView textForgotPassword;
    private TextView newAccount;
    private Button buttonSignIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewModel once
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(AuthViewModel.class);

        // Observe sign-in result
        viewModel.getFirebaseUserMutableLiveData()
                .observe(this, new Observer<FirebaseUser>() {
                    @Override
                    public void onChanged(FirebaseUser firebaseUser) {
                        if (firebaseUser != null) {
                            // Navigate to List on successful sign-in
                            navController.navigate(R.id.action_signInFragment_to_listFragment);
                        } else {
                            Toast.makeText(
                                    requireContext(),
                                    "Login failed. Please check credentials.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // Bind views
        editEmail           = view.findViewById(R.id.editEmail);
        editPassword        = view.findViewById(R.id.editPassword);
        textForgotPassword  = view.findViewById(R.id.textForgotPassword);
        newAccount          = view.findViewById(R.id.newAccount);
        buttonSignIn        = view.findViewById(R.id.buttonSignIn);

        // Navigate to sign-up
        newAccount.setOnClickListener(v ->
                navController.navigate(R.id.action_signInFragment_to_signupFragment)
        );

        // Handle sign-in click
        buttonSignIn.setOnClickListener(v -> {
            String email    = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                        requireContext(),
                        "Please enter both email and password",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Trigger sign-in; observer in onCreate() will handle navigation/toasts
            viewModel.signIn(email, password);
            Toast.makeText(
                    requireContext(),
                    "Signing in…",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}
