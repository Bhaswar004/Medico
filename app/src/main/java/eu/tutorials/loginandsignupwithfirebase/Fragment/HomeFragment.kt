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
            add(SlideModel("https://bit.ly/2YoJ77H", "", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://bit.ly/3fLJf72", "And people do that.", ScaleTypes.CENTER_CROP))
        }

        // Set image list to the first slider
        imageSlider1.setImageList(imageList1)

        // Create image list for the second slider
        val imageList2 = ArrayList<SlideModel>().apply {
            add(SlideModel("https://bit.ly/2YoJ77H", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP))
            add(SlideModel("https://bit.ly/3fLJf72", "And people do that.", ScaleTypes.CENTER_CROP))
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
