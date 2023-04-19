package com.example.sqliteauth

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var name_edt: EditText
    lateinit var email_edt: EditText
    lateinit var phone_edt: EditText
    lateinit var password_edt: EditText
    lateinit var register_button: Button
    lateinit var login_button: Button
    lateinit var db: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name_edt = findViewById(R.id.edt_name)
        email_edt = findViewById(R.id.edt_email)
        phone_edt = findViewById(R.id.edt_phone)
        password_edt = findViewById(R.id.edt_password)
        register_button = findViewById(R.id.register_btn)
        login_button = findViewById(R.id.login_btn)

        //creating a database
        db = openOrCreateDatabase("LADB", Context.MODE_PRIVATE, null)

        //creating a table
        db.execSQL("CREATE TABLE IF NOT EXISTS users(jina VARCHAR, arafa VARCHAR, simu VARCHAR, siri VARCHAR)")

        register_button.setOnClickListener{
            var editname = name_edt.text.toString().trim()
            var editemail = email_edt.text.toString().trim()
            var  editphone = phone_edt.text.toString().trim()
            var  editpassword = password_edt.text.toString().trim()

            //validate user input
            if (editname.isEmpty() || editemail.isEmpty() || editphone.isEmpty() || editpassword.isEmpty()){
                Toast.makeText(this, "Cannot submit empty fields", Toast.LENGTH_SHORT).show()
            } else{
                //proceed to dump data
                db.execSQL("INSERT INTO users VALUES('"+editname+"','"+editemail+"','"+editphone+"','"+editpassword+"')")

                Toast.makeText(this, "Account created Successfully", Toast.LENGTH_SHORT).show()

                // intent to login
                var gotologin = Intent(this, loginActivity::class.java)
                startActivity(gotologin)
            }
        }


    }
}