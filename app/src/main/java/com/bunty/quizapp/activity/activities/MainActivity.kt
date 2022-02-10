package com.bunty.quizapp.activity.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bunty.quizapp.R
import com.bunty.quizapp.activity.adapter.QuizAdapter
import com.bunty.quizapp.activity.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizlist = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupviews()

    }

    fun setupviews(){
        setUpFireStore()
        setupDrawerlayout()
        setuprecylerview()
        setupdatepicker()
    }

    private fun setupdatepicker() {
        btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER", "Date Picker Cancelled")
            }
        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if ( value==null || error!= null ){
                Toast.makeText(this, "error  fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setuprecylerview() {
       adapter = QuizAdapter(this,quizlist)
        quizRecylerview.layoutManager = GridLayoutManager(this,2)
        quizRecylerview.adapter = adapter
    }

    fun setupDrawerlayout(){
        setSupportActionBar(findViewById(R.id.appBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(this,findViewById(R.id.maindrawer), R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            maindrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}