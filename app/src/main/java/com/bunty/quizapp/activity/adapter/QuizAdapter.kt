package com.bunty.quizapp.activity.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bunty.quizapp.R
import com.bunty.quizapp.activity.activities.QuestionActivity
import com.bunty.quizapp.activity.models.Quiz
import com.bunty.quizapp.activity.utils.ColorPicker
import com.bunty.quizapp.activity.utils.IconPicker

class QuizAdapter(var context:Context , var quizzes:List<Quiz>):RecyclerView.Adapter<QuizAdapter.quizviewholder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): quizviewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return quizviewholder(view)
    }

    override fun onBindViewHolder(holder:quizviewholder, position: Int) {

        holder.textTitle.text = quizzes[position].title
        holder.cardcontainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getcolor()))
        holder.iconview.setImageResource(IconPicker.geticon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context, quizzes[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return quizzes.size
    }

    class quizviewholder(itemview : View):RecyclerView.ViewHolder(itemview){

        var textTitle:TextView = itemview.findViewById(R.id.quiztitle)
        var iconview:ImageView = itemview.findViewById(R.id.quizicon)
        var cardcontainer:CardView = itemview.findViewById(R.id.cardcontainer)

    }
}