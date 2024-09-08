package eu.tutorials.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import eu.tutorials.loginandsignupwithfirebase.Fragment.HomeFragment

class Confirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)


        val confirmbutton = findViewById<TextView>(R.id.button2)
        confirmbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}