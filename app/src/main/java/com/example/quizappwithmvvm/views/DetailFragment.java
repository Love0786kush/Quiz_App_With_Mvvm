package com.example.quizappwithmvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.quizappwithmvvm.Adapter.QuizListAdapter;
import com.example.quizappwithmvvm.Model.QuizListModel;
import com.example.quizappwithmvvm.R;
import com.example.quizappwithmvvm.viewModel.QuizListViewModel;
import com.example.quizappwithmvvm.views.DetailFragmentArgs;
import com.example.quizappwithmvvm.views.DetailFragmentDirections;

import java.util.List;

public class DetailFragment extends Fragment implements QuizListAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NavController navController;
    private QuizListViewModel viewModel;
    private QuizListAdapter adapter;
    private int position;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the detail screen layout
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read the 'position' argument passed from ListFragment
        if (getArguments() != null) {
            position = DetailFragmentArgs.fromBundle(getArguments()).getPosition();
        }

        // Initialize ViewModel once
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(QuizListViewModel.class);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // NavController for navigation actions
        navController = Navigation.findNavController(view);

        // View bindings
        recyclerView = view.findViewById(R.id.recyclelist);
        progressBar  = view.findViewById(R.id.QuizListPrgressbar);

        // RecyclerView setup
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new QuizListAdapter(this);
        recyclerView.setAdapter(adapter);

        // Observe data and update adapter
        viewModel.getQuizListLiveData().observe(
                getViewLifecycleOwner(),
                new Observer<List<QuizListModel>>() {
                    @Override
                    public void onChanged(List<QuizListModel> quizListModels) {
                        progressBar.setVisibility(View.GONE);
                        adapter.setQuizListModels(quizListModels);
                    }
                }
        );
    }

    @Override
    public void onItemClick(int clickedPosition) {
        // Retrieve the clicked model
        QuizListModel model = adapter.getItems().get(clickedPosition);
        String quizId = model.getQuizId();
        long totalQueCount = model.getQuestionCount();

        // Navigate to QuizFragment with Safe Args
        DetailFragmentDirections.ActionDetailFragmentToQuizFragment action =
                DetailFragmentDirections.actionDetailFragmentToQuizFragment();

        navController.navigate(action);
    }
}
