package com.gkprojects.cmmsandroidapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.gkprojects.cmmsandroidapp.DataClasses.OverviewMainData
import com.gkprojects.cmmsandroidapp.R
import org.w3c.dom.Text

class MainOverviewAdapter(context: Context, private var list:ArrayList<OverviewMainData>) : RecyclerView.Adapter<MainOverviewAdapter.MyViewHolder>(){
    class MyViewHolder(itemView :View): RecyclerView.ViewHolder(itemView) {
        val title : TextView=itemView.findViewById(R.id.tv_title_home)
        val customerName : TextView=itemView.findViewById(R.id.tv_customername_home)
        //val id : TextView=itemView.findViewById(R.id.)
        val date : TextView=itemView.findViewById(R.id.dates_home)
        val urgent : TextView=itemView.findViewById(R.id.tv_urgent_name)
        val description : TextView=itemView.findViewById(R.id.tv_details_home)
        val view : View =itemView.findViewById(R.id.view_testing)


    }
    fun setData(tempList: ArrayList<OverviewMainData>) {
        this.list=tempList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_test_row,parent,false)
        return MainOverviewAdapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = list[position]
        holder.title.text=currentitem.Title
        holder.customerName.text=currentitem.CustomerName

        holder.date.text=currentitem.DateStart
        holder.urgent.text=currentitem.Urgency
        holder.description.text=currentitem.Description

        when (currentitem.Urgency) {
            "Fixed" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.color_purple_blue))
                        holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.color_purple_blue))}
            "Down" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.red))
                        holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.red))}
            "Planning" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.color_dark_green))
                holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.color_dark_green))}
            "Maintenance" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.orange))
                holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.orange))}
            "Service" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.color_light_green))
                holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.color_light_green))}
            "Demonstration" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.color_grey))
                holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.color_grey))}
            "Other" -> {holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.purple_200))
                holder.urgent.setTextColor(ContextCompat.getColor(holder.view.context, R.color.purple_200))}
            else -> holder.view.setBackgroundColor(ContextCompat.getColor(holder.view.context, R.color.white)) // Default color
        }


    }
}