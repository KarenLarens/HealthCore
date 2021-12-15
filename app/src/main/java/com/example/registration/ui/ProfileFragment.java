package com.example.registration.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.registration.R;


public class ProfileFragment extends Fragment {
    ImageButton btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        btn= view.findViewById(R.id.Btn_Emergency);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:911")));
            }
        });
        return view;
    }
}
