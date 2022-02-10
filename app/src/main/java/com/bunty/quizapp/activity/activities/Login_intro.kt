package com.bunty.quizapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.bunty.quizapp.R
import com.bunty.quizapp.activity.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth

class login_intro : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth:FirebaseAuth = FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
            Toast.makeText(this, "already logeed in", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        var getstartedbtn = findViewById<Button>(R.id.getstartedbtn)

        getstartedbtn.setOnClickListener {

            redirect("LOGIN")
        }

    }

    private fun redirect(name:String){
        val intent:Intent = when(name){
            "LOGIN" -> Intent(this, login_activity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw  Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }


}