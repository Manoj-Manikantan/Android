package com.example.assignment1ex2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /* button Click me! event method
    * using resources.getString accessing string values from strings.xml file
    * passing the values using Intent
    *   */
    fun btnClickMe_Click(v:View)
    {
        if(v.id == R.id.btnClickMe)
        {
            val fullName = resources.getString(R.string.full_name)
            val qualification = resources.getString(R.string.qualifications)
            val profession = resources.getString(R.string.profession)
            val hobby = resources.getString(R.string.hobby)
            val dreamJob = resources.getString(R.string.dream_job)

            val i = Intent(this@MainActivity, SubActivity::class.java)

            i.putExtra("fullName", fullName)
            i.putExtra("qualification", qualification)
            i.putExtra("profession", profession)
            i.putExtra("hobby", hobby)
            i.putExtra("dreamJob", dreamJob)

            startActivity(i)
        }
    }

}