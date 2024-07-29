package eu.tutorials.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.loginandsignupwithfirebase.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        binding.signInButton.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java ))
            finish()
        }
        binding.registerButton.setOnClickListener{
            val email: String = binding.email.text.toString()
            val username: String = binding.userName.text.toString()
            val password: String = binding.password.text.toString()
            val repeatpassword: String = binding.repeatPassword.text.toString()

            if(username.isEmpty() || email.isEmpty()|| password.isEmpty()|| repeatpassword.isEmpty()){
                Toast.makeText(this, "PLEASE FILL ALL THE DETAILS" , Toast.LENGTH_SHORT).show()
            } else if(password!= repeatpassword){
                Toast.makeText(this, "Repeat password must be same" ,Toast.LENGTH_SHORT ).show()
            }else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Registration is successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginActivity::class.java ))
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            }
        }

    }
