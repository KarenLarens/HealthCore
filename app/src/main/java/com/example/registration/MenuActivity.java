package com.example.registration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import android.view.GestureDetector;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.registration.ui.CentersFragment;
import com.example.registration.ui.CodeFragment;
import com.example.registration.ui.ContactFragment;
import com.example.registration.ui.HelpFragment;
import com.example.registration.ui.ProfileFragment;
import com.example.registration.ui.RateFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.registration.ui.ProfileFragment2;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final int REQUEST_PERMISSION_CALL =100;
    private static final int REQUEST_PERMISSION_LOCATION =100;
    private DrawerLayout drawer;
    private GestureDetector gestureDetector;
    Button btn_emer;

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Intent intent = new Intent(MenuActivity.this, loginActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Intent intent = new Intent(MenuActivity.this, loginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        this.gestureDetector = new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_boton:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment2()).commit();
                break;
            case R.id.nav_code:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CodeFragment()).commit();
                break;
            case R.id.nav_centers:
                if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=hospital")));
                }else{
                    if ((ContextCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                            (ContextCompat.checkSelfPermission(MenuActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=hospital")));
                    }else{
                        ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_PERMISSION_LOCATION);
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Debes habilitar los permisos de ubicación para usar esta función.",Toast.LENGTH_SHORT);
                        toast1.show();
                        if ((ContextCompat.checkSelfPermission(MenuActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                                (ContextCompat.checkSelfPermission(MenuActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=hospital")));
                        }
                    }
                }
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HelpFragment()).commit();
                break;
            case R.id.nav_rate:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RateFragment()).commit();
                break;
            case R.id.nav_sesion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CentersFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}


