package com.example.ukk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.etEmail)
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerTextView = findViewById<TextView>(R.id.tvRegister)
        val dbHelper = DatabaseHelper(this)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val role = dbHelper.checkUser(email, password)

            if (role != null) {
                val intent = if (role == "admin") {
                    Intent(this, LoginActivity::class.java)
                } else {
                    Intent(this, kegiatan::class.java)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login gagal! Periksa email dan password", Toast.LENGTH_SHORT).show()
            }
        }

        registerTextView.setOnClickListener {
            startActivity(Intent(this, loginButton::class.java))
        }
    }
}