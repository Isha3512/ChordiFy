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

class SingInActivity : AppCompatActivity() {
    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sing_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mauth=FirebaseAuth.getInstance()
        val EmailText= findViewById<EditText>(R.id.etEmailText)
        val PassText = findViewById<EditText>(R.id.etPassword)
        val Login = findViewById<Button>(R.id.btnLogin)
        val SignUp= findViewById<TextView>(R.id.tvSignUp)

        SignUp.setOnClickListener{
            Intent(this, SignUpActivity:: class.java).also{
                startActivity(it)
                finish()
            }
        }
        Login.setOnClickListener{
            val emailText= EmailText.text.toString()
            val Password= PassText.text.toString()

            if(Password.isNotEmpty() && emailText.isNotEmpty() )
            {
                mauth.signInWithEmailAndPassword(emailText, Password).addOnCompleteListener { it1 ->
                    if(it1.isSuccessful) {
                        val name=mauth.currentUser?.displayName.toString()
                        Toast.makeText(this, "Welcome $name !",Toast.LENGTH_SHORT)
                            .show()
                    }
                    else
                    {
                        Toast.makeText(this, it1.exception?.toString() + " Login Failed !", Toast.LENGTH_SHORT)
                            .show()
                    }
            }
            }
            else{
                Toast.makeText(this, "Empty Credentials !", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }
}