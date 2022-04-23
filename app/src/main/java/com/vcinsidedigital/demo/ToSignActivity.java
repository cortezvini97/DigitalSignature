package com.vcinsidedigital.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vcinsidedigital.digitalsignature.DigitalSignature;

public class ToSignActivity extends AppCompatActivity {

    private Button btnSave, btnClear;
    private DigitalSignature digitalSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_sign);

        btnSave = findViewById(R.id.btn_Save);
        btnClear = findViewById(R.id.btn_Clear);
        digitalSignature = findViewById(R.id.digitalSignature);

        digitalSignature.setOnToSignListener(new DigitalSignature.OnToSignListener() {
            @Override
            public void onStartSignature() {
                Toast.makeText(ToSignActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                btnSave.setEnabled(true);
                btnClear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnSave.setEnabled(false);
                btnClear.setEnabled(false);
            }

            @Override
            public void onSave(String tmpFile)
            {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("filePath", tmpFile);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitalSignature.save(getApplicationContext());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitalSignature.clear();
            }
        });
    }
}