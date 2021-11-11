package com.example.registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QR_Cliente extends AppCompatActivity {

    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_cliente);

        btn = findViewById(R.id.btn_compartir1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);

                view.draw(canvas);

                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                Uri uriF = null;
                try{
                    File f = File.createTempFile("sharedImage",".png",getExternalCacheDir());
                    f.deleteOnExit();
                    FileOutputStream fo= new FileOutputStream(f);
                    fo.write(stream.toByteArray());
                    fo.close();

                    uriF = Uri.fromFile(f);
                }catch (IOException e){
                    e.printStackTrace();
                }

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("image/png");

                sharingIntent.putExtra(Intent.EXTRA_STREAM,uriF);
                startActivity(Intent.createChooser(sharingIntent, "Enviar a:"));
            }
        });
    }
}