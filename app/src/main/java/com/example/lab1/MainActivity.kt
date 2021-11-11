package com.example.lab1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val LAUNCH_EXPLICIT_INTENT_AND_GET_RESULT = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForExplicitActivity = findViewById<Button>(R.id.buttonExplicitActivity)
        val buttonForImplicitActivity = findViewById<Button>(R.id.buttonForImplicitActivity)
        val buttonForActionSend = findViewById<Button>(R.id.buttonForActionSend)
        val buttonForPickAndDisplayImage = findViewById<Button>(R.id.pickAndDisplayImageButton)

        buttonForExplicitActivity.setOnClickListener(this)
        buttonForImplicitActivity.setOnClickListener(this)
        buttonForActionSend.setOnClickListener(this)
        buttonForPickAndDisplayImage.setOnClickListener(this)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val textViewResult = findViewById<TextView>(R.id.resultTextView)
            if (it.resultCode == Activity.RESULT_OK) {
                textViewResult.text = it.data?.extras!!.get("result").toString()
            } else if (it.resultCode == Activity.RESULT_CANCELED) {
                textViewResult.text = ""
            }
        }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonExplicitActivity -> {
                val intent = Intent(this, ExplicitActivity::class.java)
                getResult.launch(intent)
            }
            R.id.buttonForImplicitActivity -> {
                val implicitIntent = Intent("com.example.lab1.IMPLICIT_ACTION")
                startActivity(implicitIntent)
            }
            R.id.buttonForActionSend -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
                intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Send"))
            }
            R.id.pickAndDisplayImageButton -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.type = "image/*"
                startActivity(intent)
            }
        }
    }
}