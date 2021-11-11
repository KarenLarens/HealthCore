package com.example.registration.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.registration.MenuActivity;
import com.example.registration.R;

public class ProfileFragment extends Fragment {
    MenuActivity MainActivity = new MenuActivity();
    private static final int REQUEST_PERMISSION_CALL =100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void call(View view) {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 911")));
        }else{
            if (ContextCompat.checkSelfPermission(MainActivity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel: 911")));
            }else {
                ActivityCompat.requestPermissions(MainActivity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CALL);
                Toast toast1 = Toast.makeText(MainActivity.getApplicationContext(), "Debes habilitar el permiso de llamada para usar esta función", Toast.LENGTH_SHORT);
                toast1.show();
                if (ContextCompat.checkSelfPermission(MainActivity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 911")));
                }
            }
        }
    }
}
