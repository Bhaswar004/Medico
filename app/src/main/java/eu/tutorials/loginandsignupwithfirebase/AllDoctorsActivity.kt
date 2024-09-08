package eu.tutorials.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.loginandsignupwithfirebase.databinding.ActivityAllDoctorsBinding

class AllDoctorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllDoctorsBinding
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.images,
            R.drawable.download,
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
        )

        val name = arrayOf(
            "Emily Harper", "Jason Miller", "Olivia Bennett", "Daniel Foster", "Sophia Collins", "Ethan Brooks"
        )

        val specialization = arrayOf(
            "psychiatrist", "psychiatrist", "psychiatrist", "psychiatrist", "psychiatrist", "psychiatrist"
        )

        val rating = arrayOf("★ 5.0", "★ 5.0", "★ 5.0", "★ 5.0", "★ 5.0", "★ 5.0")

        val description = arrayOf(
            "A respected psychiatrist with a focus on geriatric mental health.",
            "A psychiatrist known for innovative treatment plans for PTSD.",
            "A skilled psychiatrist with expertise in cognitive behavioral therapy.",
            "A psychiatrist dedicated to holistic approaches in mental wellness.",
            "An experienced psychiatrist focused on anxiety and mood disorders.",
            "A compassionate psychiatrist specializing in adolescent mental health."
        )

        userArrayList = ArrayList()
        for (i in name.indices) {
            val user = User(name[i], specialization[i], description[i], imageId[i], rating[i])
            userArrayList.add(user)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = DoctorAdapter(this, userArrayList)
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val selectedUser = userArrayList[position]
            val intent = Intent(this, Booking::class.java)
            intent.putExtra("name", selectedUser.name)
            intent.putExtra("specialization", selectedUser.specialization)
            intent.putExtra("description", selectedUser.description)
            intent.putExtra("imageId", selectedUser.imageId)
            intent.putExtra("rating", selectedUser.rating)
            startActivity(intent)
        }
    }
}
