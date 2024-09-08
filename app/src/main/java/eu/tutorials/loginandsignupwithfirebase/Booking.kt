package eu.tutorials.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import eu.tutorials.loginandsignupwithfirebase.databinding.ActivityBooking2Binding

class Booking : AppCompatActivity() {

    private lateinit var binding: ActivityBooking2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooking2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent safely
        val name = intent.getStringExtra("name") ?: "Unknown"
        val specialization = intent.getStringExtra("specialization") ?: "Unknown"
        val description = intent.getStringExtra("description") ?: "No description available"
        val rating = intent.getStringExtra("rating") ?: "No rating"
        val imageId = intent.getIntExtra("imageId", R.drawable.a)

        // Set the values in the UI
        binding.doctorName.text = name
        binding.doctorSpeciality.text = specialization
        binding.detailsContent.text = description
        binding.Doctorrating.text = rating
        binding.doctorImage.setImageResource(imageId)


        val bookingButton = binding.bookAppointmentButton
        bookingButton.setOnClickListener {
            val intent = Intent(this, Confirmation::class.java)
            startActivity(intent)
        }
    }
}
