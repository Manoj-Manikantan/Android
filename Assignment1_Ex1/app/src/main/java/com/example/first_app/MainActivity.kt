package com.example.first_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /* On send button click  */
    fun btnSend_Click(v: View)
    {
        if(v.id == R.id.sendButton)
        {
            val fullName = findViewById<EditText>(R.id.editTextFullName)
            val qualification = findViewById<EditText>(R.id.editTextQualification)
            val profession = findViewById<EditText>(R.id.editTextProfession)
            val hobby = findViewById<EditText>(R.id.editTextHobby)
            val dreamJob = findViewById<EditText>(R.id.editTextDreamJob)

            val i = Intent(this@MainActivity, SubActivity::class.java)
            i.putExtra("fullName", fullName.text.toString())
            i.putExtra("qualification", qualification.text.toString())
            i.putExtra("profession", profession.text.toString())
            i.putExtra("hobby", hobby.text.toString())
            i.putExtra("dreamJob", dreamJob.text.toString())

            startActivity(i)
        }
    }

}