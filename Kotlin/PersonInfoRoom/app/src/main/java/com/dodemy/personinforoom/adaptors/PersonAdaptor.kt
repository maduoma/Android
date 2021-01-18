package com.dodemy.personinforoom.adaptors

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.personinforoom.R
import com.dodemy.personinforoom.activities.EditActivity
import com.dodemy.personinforoom.constants.Constants
import com.dodemy.personinforoom.database.AppDatabase
import com.dodemy.personinforoom.model.Person


class PersonAdaptor(private val context: Context) : RecyclerView.Adapter<PersonAdaptor.MyViewHolder>() {
    private var mPersonList: List<Person>? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.name.text = mPersonList!![i].name
        myViewHolder.name.text = mPersonList!![i].email
        myViewHolder.name.text = mPersonList!![i].number
        myViewHolder.name.text = mPersonList!![i].pinCode
        myViewHolder.name.text = mPersonList!![i].city
    }

    override fun getItemCount(): Int {
        return if (mPersonList == null) { 0
        } else mPersonList!!.size
    }

    var tasks: List<Person>?
        get() = mPersonList
        set(personList) {
            mPersonList = personList
            notifyDataSetChanged()
        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.person_name)
        var email: TextView = itemView.findViewById(R.id.person_email)
        var pincode: TextView = itemView.findViewById(R.id.person_pincode)
        var number: TextView = itemView.findViewById(R.id.person_number)
        var city: TextView = itemView.findViewById(R.id.person_city)
        var editImage: ImageView = itemView.findViewById(R.id.edit_Image)
        var mDb: AppDatabase? = AppDatabase.getInstance(context)

        init {
            editImage.setOnClickListener {
                    val elementId: Int = mPersonList!![adapterPosition].id
                    val i = Intent(context, EditActivity::class.java)
                    i.putExtra(Constants.UPDATE_Person_Id, elementId)
                    context.startActivity(i)
            }
        }
    }

}
