package com.bunty.quizapp.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bunty.quizapp.R
import com.bunty.quizapp.activity.models.Question


class OptionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.optionviewholder>() {


    private var options: List<String> =
        listOf(question.option1, question.option2, question.option3, question.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionviewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return optionviewholder(view)
    }

    override fun onBindViewHolder(holder: optionviewholder, position: Int) {
        holder.optionview.text = options[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show()
            question.useranswer = options[position]
            notifyDataSetChanged()
        }
        if (question.useranswer == options[position]) {

            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }


    class optionviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val optionview = itemview.findViewById<TextView>(R.id.quiz_option)
    }
}