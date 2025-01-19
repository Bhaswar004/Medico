package eu.tutorials.loginandsignupwithfirebase.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import eu.tutorials.loginandsignupwithfirebase.AllDoctorsActivity
import eu.tutorials.loginandsignupwithfirebase.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize ImageSliders
        val imageSlider1 = view.findViewById<ImageSlider>(R.id.image_slider1)
        val imageSlider2 = view.findViewById<ImageSlider>(R.id.image_slider2)

        // Create image list for the first slider
        val imageList1 = ArrayList<SlideModel>().apply {
            add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpQbDmF11RXj-eEkim_1yKz_uGmEZhK0xZNg&s", "Consult to a doctor any time", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://www.freewebheaders.com/wp-content/gallery/healthcare_1/cache/medical-doctor-stethoscope-and-office-sign-web-header.jpg-nggid044058-ngg0dyn-1280x375x100-00f0w010c010r110f110r010t010.jpg", "we have the best doctors.", ScaleTypes.CENTER_CROP,))
            add(SlideModel("https://static.vecteezy.com/system/resources/previews/002/700/984/non_2x/online-doctor-and-healthcare-web-banner-design-a-man-a-consulting-doctor-on-a-health-app-online-doctor-application-header-or-footer-banner-free-vector.jpg", "24x7 service available", ScaleTypes.FIT))
        }

        // Set image list to the first slider
        imageSlider1.setImageList(imageList1)

        // Create image list for the second slider
        val imageList2 = ArrayList<SlideModel>().apply {
            add(SlideModel("https://static.vecteezy.com/system/resources/thumbnails/026/375/249/small_2x/ai-generative-portrait-of-confident-male-doctor-in-white-coat-and-stethoscope-standing-with-arms-crossed-and-looking-at-camera-photo.jpg", "Dr.A(Therapist)", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://images.theconversation.com/files/304957/original/file-20191203-66986-im7o5.jpg?ixlib=rb-4.1.0&q=45&auto=format&w=926&fit=clip", "Dr.B(pediatrician)", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://static.vecteezy.com/system/resources/thumbnails/028/287/555/small_2x/an-indian-young-female-doctor-isolated-on-green-ai-generated-photo.jpg", "Dr.C(Dermatologist)", ScaleTypes.CENTER_CROP))
        }

        // Set image list to the second slider
        imageSlider2.setImageList(imageList2)

        val allDoctorScreenButton = view.findViewById<Button>(R.id.button)
        allDoctorScreenButton.setOnClickListener {
            val intent = Intent(requireContext(), AllDoctorsActivity::class.java)
            startActivity(intent)
        }

        val allDoctorScreenButton2 = view.findViewById<TextView>(R.id.textView24)
        allDoctorScreenButton2.setOnClickListener {
            val intent = Intent(requireContext(), AllDoctorsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        // Any static methods or variables can go here
    }
}
