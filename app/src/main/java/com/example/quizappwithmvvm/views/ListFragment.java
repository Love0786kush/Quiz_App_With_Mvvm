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
import com.example.quizappwithmvvm.views.ListFragmentDirections;

import java.util.List;

public class ListFragment extends Fragment implements QuizListAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NavController navController;
    private QuizListViewModel viewModel;
    private QuizListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclelist);
        progressBar = view.findViewById(R.id.QuizListPrgressbar);
        navController = Navigation.findNavController(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new QuizListAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
        ).get(QuizListViewModel.class);

        viewModel.getQuizListLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModels) {
                progressBar.setVisibility(View.GONE);
                adapter.setQuizListModels(quizListModels);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Use Safe Args to navigate and pass data in one call
        ListFragmentDirections.ActionListFragmentToDetailFragment action =
                ListFragmentDirections.actionListFragmentToDetailFragment(position);
        navController.navigate(action);
    }
}
