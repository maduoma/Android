package com.dodemy.todolistwithroomtesting.list

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.todolistwithroomtesting.R
import com.dodemy.todolistwithroomtesting.data.ToDo
import java.util.*
import kotlin.collections.ArrayList

class ToDoAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ToDoAdapter.TodoHolder>() {
    private var mAllToDos: List<ToDo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mAllToDos.size
    }

    fun setToDos(toDos: List<ToDo>) {
        this.mAllToDos = toDos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(mAllToDos[position], onClickListener)
    }

    inner class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val completed = itemView.findViewById<CheckBox>(R.id.completed)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val start = itemView.findViewById<TextView>(R.id.start)
        private val dueLabel = itemView.findViewById<TextView>(R.id.due_label)
        private val due = itemView.findViewById<TextView>(R.id.due)
        private val card = itemView.findViewById<CardView>(R.id.card)

        fun bind(toDo: ToDo, clickListener: OnClickListener) {
            completed.isChecked = toDo.completed
            completed.setOnClickListener { clickListener.onCheckboxChecked(toDo.id) }

            title.text = toDo.title
            val calendar = Calendar.getInstance()
            val dateFormat = DateFormat.getDateFormat(itemView.context)

            calendar.timeInMillis = toDo.created
            start.text = dateFormat.format(calendar.time)

            if (toDo.dueDate != null) {
                calendar.timeInMillis = toDo.dueDate!!
                due.text = dateFormat.format(calendar.time)
            } else {
                due.visibility = View.INVISIBLE
                dueLabel.visibility = View.INVISIBLE
            }

            card.setCardBackgroundColor(
                itemView.context.getColor(
                    determineCardColor(
                        toDo.dueDate,
                        toDo.completed
                    )
                )
            )
        }
    }

    interface OnClickListener {
        fun onCheckboxChecked(id: String)
    }
}