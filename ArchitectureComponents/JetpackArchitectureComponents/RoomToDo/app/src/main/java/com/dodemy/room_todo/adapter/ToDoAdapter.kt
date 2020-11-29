package com.dodemy.room_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.room_todo.R
import com.dodemy.room_todo.databinding.ItemTodoRecyclerviewBinding
import com.dodemy.room_todo.enums.Priorities
import com.dodemy.room_todo.model.ToDo
import com.dodemy.room_todo.fragments.RecyclerViewFragmentDirections


class ToDoAdapter(private val context: Context): RecyclerView.Adapter<ToDoAdapter.ToDoHolder>()
{
    private var listOfToDos = emptyList<ToDo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ToDoHolder
    {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemTodoRecyclerviewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_todo_recyclerview, parent, false
        )

        return ToDoHolder(binding)
    }

    override fun getItemCount() = listOfToDos.size

    internal fun addToDos(listOfToDos: List<ToDo>)
    {
        this.listOfToDos = listOfToDos
        notifyDataSetChanged()
    }
    internal fun getItemFromPosition(position: Int): ToDo
    {
        return listOfToDos[position]
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ToDoHolder, position: Int)
    {
        holder.bindToDo(listOfToDos[position])
    }

    inner class ToDoHolder(val binding: ItemTodoRecyclerviewBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener
    {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bindToDo(toDo: ToDo)
        {
            binding.tvTitle.text = toDo.title
            binding.tvDueDate.text = toDo.dueDate
            binding.tvDescription.text = toDo.description
            when (toDo.priority)
            {
                Priorities.LOW.name -> binding.ivPriority.setBackgroundResource(R.drawable.prio_green)
                Priorities.MEDIUM.name -> binding.ivPriority.setBackgroundResource(R.drawable.prio_orange)
                Priorities.HIGH.name -> binding.ivPriority.setBackgroundResource(R.drawable.prio_red)
            }
        }

        override fun onClick(view: View) {
            val navController: NavController = Navigation.findNavController(view)
            val action = RecyclerViewFragmentDirections.actionRecyclerFragmentToAddToDoDialog(listOfToDos[adapterPosition], adapterPosition)
            navController.navigate(action)
        }
    }
}