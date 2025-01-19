package eu.tutorials.loginandsignupwithfirebase

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(private val context: Activity, private val arrayList: ArrayList<User>) : ArrayAdapter<User>(context, R.layout.doctor_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        // Reuse the view if possible
        if (convertView == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.doctor_list, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Set the data for the current user
        val currentUser = arrayList[position]
        viewHolder.imageView.setImageResource(currentUser.imageId)
        viewHolder.name.text = currentUser.name
        viewHolder.description.text = currentUser.description
        viewHolder.rating.text = currentUser.rating
        viewHolder.specialization.text = currentUser.specialization

        return view
    }

    // ViewHolder pattern to optimize view lookup
    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.profilePic)
        val name: TextView = view.findViewById(R.id.DrName)
        val description: TextView = view.findViewById(R.id.Desc)
        val rating: TextView = view.findViewById(R.id.rating)
        val specialization: TextView = view.findViewById(R.id.spec)
    }
}

class DoctorAdapterRecyclerView(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapterRecyclerView.DoctorViewHolder>() {

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
