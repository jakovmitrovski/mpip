package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.app.Activity

import android.content.Intent




class ExplicitActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        val buttonOK = findViewById<Button>(R.id.buttonVoRed)
        val buttonCancel = findViewById<Button>(R.id.buttonOtkazi)

        buttonOK.setOnClickListener(this)
        buttonCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonVoRed -> {
                val returnIntent = Intent()
                val editTextVal = findViewById<EditText>(R.id.editTextForValue)
                returnIntent.putExtra("result", editTextVal.text)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
            R.id.buttonOtkazi -> {
                val returnIntent = Intent()
                setResult(RESULT_CANCELED, returnIntent)
                finish()
            }
        }
    }
}