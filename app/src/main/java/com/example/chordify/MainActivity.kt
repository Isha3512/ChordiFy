package com.example.chordify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnEmail= findViewById<Button>(R.id.btEmail)
        val btnPhone= findViewById<Button>(R.id.btPhone)
        val btnSignUp = findViewById<Button>(R.id.btSignUp)

        btnSignUp.setOnClickListener{
            Intent(this, SignUpActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        btnEmail.setOnClickListener{
            Intent(this, SingInActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }
    }
}