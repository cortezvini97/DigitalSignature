package com.vcinsidedigital.demokotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity()
{
    var imageView:ImageView? = null
    var btnToSign:Button? = null
    var tmpFile:String? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnToSign = findViewById(R.id.btn_toSign)

        btnToSign?.setOnClickListener {
            finish()
            val intent:Intent = Intent(applicationContext, ToSignActivity::class.java)
            startActivity(intent)
        }

        if(intent.getStringExtra("filePath") != null)
        {
            tmpFile = intent.getStringExtra("filePath").toString()

            val file:File = File(tmpFile)

            val bitmap:Bitmap = BitmapFactory.decodeFile(file.absolutePath)

            imageView?.setImageBitmap(bitmap)

        }

    }
}