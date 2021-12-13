package com.example.registration.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.registration.MenuActivity;
import com.example.registration.R;
import com.google.zxing.integration.android.IntentIntegrator;

public class CodeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        Button fernando = view.findViewById(R.id.Scaner);
        fernando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).initiateScan();
            }
        });
        return view;
    }
}