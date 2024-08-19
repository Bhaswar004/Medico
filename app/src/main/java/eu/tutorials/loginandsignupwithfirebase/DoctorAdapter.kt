package eu.tutorials.loginandsignupwithfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val doctor = doctors[adapterPosition]
                onDoctorClick(doctor)
            }
        }

        fun bind(doctor: Doctor) {
            itemView.findViewById<TextView>(R.id.doctorName).text = doctor.name
            itemView.findViewById<TextView>(R.id.doctorDescription).text = doctor.description
            itemView.findViewById<TextView>(R.id.appointmentTime).text = doctor.time
            itemView.findViewById<TextView>(R.id.counter).text = doctor.counter.toString()
            itemView.findViewById<ImageView>(R.id.doctorImage).setImageResource(doctor.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_chat_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int = doctors.size
}