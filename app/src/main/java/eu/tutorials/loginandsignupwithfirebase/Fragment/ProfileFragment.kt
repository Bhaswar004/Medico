package eu.tutorials.loginandsignupwithfirebase.Fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import eu.tutorials.loginandsignupwithfirebase.R
import java.util.UUID


class ProfileFragment : Fragment() {
    private lateinit var profileImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: Button

    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        profileImageView = view.findViewById(R.id.profile_image)
        nameEditText = view.findViewById(R.id.name_id)
        addressEditText = view.findViewById(R.id.address_id)
        emailEditText = view.findViewById(R.id.email_id)
        phoneNumberEditText = view.findViewById(R.id.phone_id)
        saveButton = view.findViewById(R.id.button)


        profileImageView.setOnClickListener{
            openGallery()
        }
        saveButton.setOnClickListener {
            saveInformation()
        }
        return view
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            profileImageView.setImageURI(imageUri)
        }
    }

    private fun saveInformation() {
        val name = nameEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val phoneNumber = phoneNumberEditText.text.toString().trim()


        val userMap = hashMapOf(
            "name" to name,
            "address" to address,
            "email" to email,
            "phoneNumber" to phoneNumber
        )
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .add(userMap)
            .addOnSuccessListener {
                // Handle successful save
                Toast.makeText(requireContext(), "Information saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Handle failure
                Toast.makeText(requireContext(), "Failed to save information: ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun uploadImageToFirebase() {
        val storageRef = FirebaseStorage.getInstance().reference.child("profile_images/${UUID.randomUUID()}.jpg")
        imageUri?.let {
            storageRef.putFile(it)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}