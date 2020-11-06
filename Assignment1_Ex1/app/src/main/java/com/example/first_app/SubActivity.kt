package com.example.first_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val j = intent

        val textViewFullName = findViewById<TextView>(R.id.textViewFullName)
        val textViewQualification = findViewById<TextView>(R.id.textViewQualifications)
        val textViewProfession = findViewById<TextView>(R.id.textViewProfession)
        val textViewHobby = findViewById<TextView>(R.id.textViewHobby)
        val textViewDreamJob = findViewById<TextView>(R.id.textViewDreamJob)

        textViewFullName.text = "FullName : " + j.getStringExtra("fullName")
        textViewQualification.text = "Qualifications : " + j.getStringExtra("qualification")
        textViewProfession.text = "Profession : " + j.getStringExtra("profession")
        textViewHobby.text = "Hobby : " + j.getStringExtra("hobby")
        textViewDreamJob.text = "DreamJob : " + j.getStringExtra("dreamJob")
    }
}