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

public class SignupFragment extends Fragment {
    private AuthViewModel viewModel;
    private NavController navController;

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private TextView alreadySignIn;
    private Button buttonSignup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewModel once
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(AuthViewModel.class);

        // Observe sign-up result
        viewModel.getFirebaseUserMutableLiveData()
                .observe(this, new Observer<FirebaseUser>() {
                    @Override
                    public void onChanged(FirebaseUser firebaseUser) {
                        if (firebaseUser != null) {
                            // Navigate to SignIn on successful registration
                            navController.navigate(R.id.action_signupFragment_to_signInFragment);
                        } else {
                            // Optionally show error if needed
                            Toast.makeText(requireContext(),
                                    "Registration failed. Please try again.",
                                    Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // View bindings
        editUsername   = view.findViewById(R.id.editUsername);
        editEmail      = view.findViewById(R.id.editEmail);
        editPassword   = view.findViewById(R.id.editPassword);
        alreadySignIn  = view.findViewById(R.id.AlreadySignIn);
        buttonSignup   = view.findViewById(R.id.buttonSignup);

        // Navigate to SignIn if user taps "Already Sign In"
        alreadySignIn.setOnClickListener(v ->
                navController.navigate(R.id.action_signupFragment_to_signInFragment)
        );

        // Sign-up button handler
        buttonSignup.setOnClickListener(v -> {
            String username = editUsername.getText().toString().trim();
            String email    = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(),
                        "Please enter both email and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Trigger sign-up; observer in onCreate() will handle navigation
            viewModel.signUp(email, password);
            Toast.makeText(getContext(),
                    "Registering…",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
