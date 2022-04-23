package com.vcinsidedigital.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vcinsidedigital.digitalsignature.DigitalSignature;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    private ImageView imageView;
    private Button btnToSign;
    private String tmpFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnToSign = findViewById(R.id.btn_toSign);

        tmpFile = (String) getIntent().getStringExtra("filePath");

        if(tmpFile != null)
        {
            File file = new File(tmpFile);

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        btnToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(getApplicationContext(), ToSignActivity.class);
                startActivity(intent);
            }
        });

    }
}