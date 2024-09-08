package eu.tutorials.loginandsignupwithfirebase

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

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
