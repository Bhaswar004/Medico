package eu.tutorials.loginandsignupwithfirebase.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.loginandsignupwithfirebase.ChatPageActivity
import eu.tutorials.loginandsignupwithfirebase.Doctor
import eu.tutorials.loginandsignupwithfirebase.DoctorAdapterRecyclerView
import eu.tutorials.loginandsignupwithfirebase.R

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorAdapter: DoctorAdapterRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewChat)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Sample data
        val doctorList = listOf(
            Doctor("Dr. Upul", "Worem consectetur adipiscing elit.", R.drawable.profile, "12.50", 2),
            Doctor("Dr. Smith", "Expert in cardiology.", R.drawable.profile, "10.00", 5)
            // Add more doctors here
        )

        doctorAdapter = DoctorAdapterRecyclerView(doctorList) { doctor ->
            // Handle doctor click
            val intent = Intent(requireContext(), ChatPageActivity::class.java).apply {
                putExtra("doctor_name", doctor.name)
                putExtra("doctor_description", doctor.description)
                putExtra("doctor_time", doctor.time)
                putExtra("doctor_counter", doctor.counter)
            }
            startActivity(intent)
        }
        recyclerView.adapter = doctorAdapter

        return view
    }
}
