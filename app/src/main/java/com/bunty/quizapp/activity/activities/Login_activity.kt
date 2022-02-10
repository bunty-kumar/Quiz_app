package com.bunty.quizapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bunty.quizapp.R
import com.bunty.quizapp.activity.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth

class login_activity : AppCompatActivity() {

    lateinit var firebaseauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        var loginbtn = findViewById<Button>(R.id.textlogin)
        var textsignup = findViewById<TextView>(R.id.textsignup)

        firebaseauth = FirebaseAuth.getInstance()

        loginbtn.setOnClickListener {
            loginuser()
        }

        textsignup.setOnClickListener{
            var intent = Intent(this@login_activity, sinup_activity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun loginuser(){

        var email:String = findViewById<EditText>(R.id.etEmailAddress).text.toString()
        var password:String = findViewById<EditText>(R.id.etpassword).text.toString()


        if (email.isBlank()||password.isBlank()){
            Toast.makeText(this, "email and password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseauth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{

                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
}