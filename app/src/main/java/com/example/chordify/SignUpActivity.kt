package com.example.chordify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth=FirebaseAuth.getInstance()
        val etUserName= findViewById<EditText>(R.id.etUsername)
        val etEmail= findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPass)
        val etRePass= findViewById<EditText>(R.id.etRePass)
        val btnCreateAcc= findViewById<Button>(R.id.btnCreatAcc)
        val Signin =findViewById<TextView>(R.id.tvSignIn)

        Signin.setOnClickListener{
            Intent(this, SingInActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        btnCreateAcc.setOnClickListener{
            val UserName= etUserName.text.toString()
            val Email = etEmail.text.toString()
            val Pass= etPass.text.toString()
            val RePass= etRePass.text.toString()

            if(Email.isNotEmpty() && Pass.isNotEmpty() && RePass.isNotEmpty() && UserName.isNotEmpty())
            {
                if(Pass == RePass)
                {
                    if(Pass.length<=6)
                    {
                        Toast.makeText(this, "Password too short !", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else
                    {
                        auth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener{it1->
                            if(it1.isSuccessful)
                            {
                                Toast.makeText(this, "Account Creation Successful", Toast.LENGTH_SHORT)
                                    .show()

                                auth.currentUser?.let {
                                    val profileUpdates= UserProfileChangeRequest.Builder()
                                        .setDisplayName(UserName)
                                        .build()
                                    it.updateProfile(profileUpdates)
                                }
                                Intent(this, SingInActivity::class.java).also {it2->
                                    startActivity(it2)
                                    finish()
                                }
                            }
                            else
                            {
                                Toast.makeText(this, "Account Creation Unsuccessful !! Try Again !", Toast.LENGTH_LONG)
                                    .show()
                            }

                        }
                    }

                }
                else{
                    Toast.makeText(this, "Passwords do not match !",Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else{
                Toast.makeText(this ,"Empty Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}