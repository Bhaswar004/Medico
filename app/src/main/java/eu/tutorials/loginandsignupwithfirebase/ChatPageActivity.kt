package eu.tutorials.loginandsignupwithfirebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ChatPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_page)


        val doctorName = intent.getStringExtra("doctor_name")


        val nameTextView = findViewById<TextView>(R.id.tvDoctorName)


        nameTextView?.text = doctorName
    }
}