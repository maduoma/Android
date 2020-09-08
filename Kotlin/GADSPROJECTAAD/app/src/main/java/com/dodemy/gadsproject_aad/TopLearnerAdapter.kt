package com.dodemy.gadsproject_aad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dodemy.gadsproject_aad.model.TopLearner
import java.text.MessageFormat
import java.util.*

class TopLearnersAdapter(var fragment: Fragment) : RecyclerView.Adapter<TopLearnersAdapter.LearnerViewHolder>() {
    private var topLearnersList: List<TopLearner> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {
        return LearnerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_learner_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        holder.learnersName.text = topLearnersList[position].name
        holder.skillHour.text = MessageFormat.format(
            "{0} Learning hours ,", String.Companion.format(
                topLearnersList[position].hours.toString()
            )
        )
        holder.learnersCountry.text = topLearnersList[position].country
        Glide.with(fragment).load(topLearnersList[position].badgeUrl).into(holder.img2)
    }

    override fun getItemCount(): Int {
        return topLearnersList.size
    }

    fun setList(list: List<TopLearner?>) {
        topLearnersList = list as List<TopLearner>
        notifyDataSetChanged()
    }

    class LearnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var learnersName: TextView = itemView.findViewById(R.id.learnersName)
        var skillHour: TextView = itemView.findViewById(R.id.learnersHour)
        var learnersCountry: TextView = itemView.findViewById(R.id.learnersCountry)
        var img2: ImageView = itemView.findViewById(R.id.learnersImg)

    }
}