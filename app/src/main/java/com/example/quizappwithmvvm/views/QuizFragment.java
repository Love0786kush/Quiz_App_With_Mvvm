package com.example.quizappwithmvvm.views;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quizappwithmvvm.R;
import com.example.quizappwithmvvm.views.QuizFragmentArgs;

public class QuizFragment extends Fragment {

    private String quizId;
    private long totalQueCount;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Receive arguments from Safe Args
        if (getArguments() != null) {
            QuizFragmentArgs args = QuizFragmentArgs.fromBundle(getArguments());
            quizId = args.getQuizId();
            totalQueCount = args.getTotalQueCount();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }
}
