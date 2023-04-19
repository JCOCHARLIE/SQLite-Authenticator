package com.example.sqliteauth

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class loginActivity : AppCompatActivity() {
    lateinit var edtemail:EditText
    lateinit var edtpassword:EditText
    lateinit var registerbutton:Button
    lateinit var loginbutton:Button
    lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtemail = findViewById(R.id.email_edit)
        edtpassword = findViewById(R.id.password_edit)
        registerbutton = findViewById(R.id.RegisterBtn)
        loginbutton = findViewById(R.id.LoginBtn)

        db = openOrCreateDatabase("LADB", Context.MODE_PRIVATE, null)

        registerbutton.setOnClickListener {
            var gotoregister = Intent(this, MainActivity::class.java)
            startActivity(gotoregister)
        }

        loginbutton.setOnClickListener {

                val email = edtemail.text.toString().trim()
                val password = edtpassword.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
                } else {
                    val cursor = db.rawQuery("SELECT * FROM users WHERE arafa=? AND siri=?", arrayOf(email, password))




                    if (cursor != null && cursor.moveToFirst()) {
                        // user is authenticated, start a new activity

                        Toast.makeText(this, "Successfull Login", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Invalid email or password, please try again", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
