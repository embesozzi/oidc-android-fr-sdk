package com.mbesozzi.forgerock;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.forgerock.android.auth.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    private static final String TAG = FirstFragment.class.getSimpleName();
    private TokenModel tokenModel;
    TextView username, button, intro ;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d(TAG,"Testing log...");
        tokenModel = new ViewModelProvider(getActivity()).get(TokenModel.class);
        tokenModel.getItem().observe(getActivity(), new Observer<String>(){
            @Override
            public void onChanged(@Nullable String updatedObject) {
                Logger.debug(TAG, "onChanged: recieved freshObject" + updatedObject);
                Log.d(TAG,"Testing log..." + updatedObject);
                if (updatedObject != null) {
                    intro.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    username.setText("Welcome " +   updatedObject);
                }
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        username = view.findViewById(R.id.textview_username);
        username.setVisibility(View.GONE);
        intro = view.findViewById(R.id.textview_first);
        button = view.findViewById(R.id.button_first);
        button.setVisibility(View.GONE);
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}