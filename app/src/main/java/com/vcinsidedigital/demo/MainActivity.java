package com.vcinsidedigital.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vcinsidedigital.digitalsignature.DigitalSignature;

public class MainActivity extends AppCompatActivity
{

    private DigitalSignature digitalSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}