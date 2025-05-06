package com.example.quizappwithmvvm.views;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.quizappwithmvvm.R;
import com.example.quizappwithmvvm.viewModel.AuthViewModel;

public class SplashFragment extends Fragment {

    private AuthViewModel viewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(AuthViewModel.class);

        // Initialize NavController
        navController = Navigation.findNavController(view);

        // Navigate after delay
        new Handler().postDelayed(() -> {
            if (viewModel.getCurrentUser() != null) {
                navController.navigate(R.id.action_splashFragment_to_listFragment);
            } else {
                navController.navigate(R.id.action_splashFragment_to_signInFragment);
            }
        }, 5000); // Delay for 5 seconds
    }
}
