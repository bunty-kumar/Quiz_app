package com.bunty.quizapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bunty.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class sinup_activity : AppCompatActivity() {

    lateinit var firebaseauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinup_activity)

        val btnsignup = findViewById<Button>(R.id.btnsignup)
        var textlogin = findViewById(R.id.textlogin) as TextView


        firebaseauth = FirebaseAuth.getInstance()

        btnsignup.setOnClickListener {
            signup()
        }

        textlogin.setOnClickListener{
            var intent = Intent(this@sinup_activity, login_activity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private  fun signup(){
        val email:String = findViewById<EditText>(R.id.etEmailAddress).text.toString()
        val password:String = findViewById<EditText>(R.id.etpassword).text.toString()
        val confirmpassword:String = findViewById<EditText>(R.id.etconfirmpassword).text.toString()

        if (email.isBlank()|| password.isBlank()|| confirmpassword.isBlank()) {
            Toast.makeText(this, "email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password!=confirmpassword){
            Toast.makeText(this, "password and confirm password don't match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseauth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){

                if (it.isSuccessful){
                    Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "error creating user", Toast.LENGTH_SHORT).show()
                }
            }
    }

}