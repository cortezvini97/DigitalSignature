package com.vcinsidedigital.demokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.vcinsidedigital.digitalsignature.DigitalSignature

class ToSignActivity : AppCompatActivity()
{
    var btnSave:Button? = null
    var btnClear:Button? = null
    var digitalSignature:DigitalSignature? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_sign)
        btnSave = findViewById(R.id.btn_Save)
        btnClear = findViewById(R.id.btn_Clear)
        digitalSignature = findViewById(R.id.digitalSignature)

        digitalSignature?.setOnToSignListener(object: DigitalSignature.OnToSignListener{
            override fun onStartSignature() {
                Toast.makeText(applicationContext, "Start", Toast.LENGTH_SHORT).show()
            }

            override fun onSigned() {
                btnSave?.isEnabled = true
                btnClear?.isEnabled = true
            }

            override fun onClear()
            {
                btnSave?.isEnabled = false
                btnClear?.isEnabled = false
            }

            override fun onSave(tmpFile: String?)
            {
                finish()
                val intent:Intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("filePath", tmpFile)
                startActivity(intent)
            }
        })

        btnSave?.setOnClickListener {
            digitalSignature?.save(applicationContext)
        }

        btnClear?.setOnClickListener {
            digitalSignature?.clear()
        }
    }
}