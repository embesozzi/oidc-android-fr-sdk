package com.mbesozzi.forgerock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.forgerock.android.auth.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private static final String TAG = SecondFragment.class.getSimpleName();
    private TokenModel tokenModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Logger.set(Logger.Level.DEBUG);
        tokenModel = new ViewModelProvider(((MainActivity) getActivity())).get(TokenModel.class);

        tokenModel.item.observe(getActivity(), new Observer<String>(){
            @Override
            public void onChanged(@Nullable String updatedObject) {
                Logger.debug(TAG, "onChanged: recieved freshObject" + updatedObject);
                if (updatedObject != null) {
                    // Do what you want with your updated object here.
                }
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.debug(TAG,"Login..." + tokenModel.getJsonTokens());
        ((TextView) view.findViewById(R.id.textview_second)).setText(tokenModel.getJsonTokens());
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}