package com.dodemy.gadsproject_aad


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.gadsproject_aad.model.SkillIQ
import com.squareup.picasso.Picasso
import java.util.*

class SkillIQAdapter : RecyclerView.Adapter<SkillIQAdapter.PostViewHolder>() {
    private var skillList: List<SkillIQ> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.skill_iq_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //setText("" + name);
        holder.skillName.text = skillList[position].name
        holder.skillScore.text = "Skill IQ Score "+skillList[position].score
        holder.skillCountry.text = skillList[position].country
        Picasso.get().load(skillList[position].badgeUrl).into(holder.img)
        Log.d(TAG, "ImageUrl is : " + skillList[position].badgeUrl)
    }

    override fun getItemCount(): Int {
        return skillList.size
    }

    fun setList(list: List<SkillIQ?>) {
        skillList = list as List<SkillIQ>
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var skillName: TextView = itemView.findViewById(R.id.skillName)
        var skillScore: TextView = itemView.findViewById(R.id.skillScore)
        var skillCountry: TextView = itemView.findViewById(R.id.skillCountry)
        var img: ImageView = itemView.findViewById(R.id.skillImg2)

    }

    companion object {
        private const val TAG = "SkillIQAdapter"
    }
}