package eu.tutorials.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.tutorials.loginandsignupwithfirebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if(currentUser!= null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener{
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()

            if(userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all the details" , Toast.LENGTH_SHORT).show()
            }else {
                auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Sign-In successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, "Sign-In failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    binding.signUpButton.setOnClickListener{
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }
    }
}
