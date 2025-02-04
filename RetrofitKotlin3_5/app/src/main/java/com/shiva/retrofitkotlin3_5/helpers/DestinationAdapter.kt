package com.shiva.retrofitkotlin3_5.helpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shiva.retrofitkotlin3_5.activities.DestinationDetailActivity
import com.shiva.retrofitkotlin3_5.models.Destination
import com.shiva.retrofitkotlin3_5.R

class DestinationAdapter(private val destinationList: List<Destination>) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.destination = destinationList[position]
        holder.txvDestination.text = destinationList[position].city

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DestinationDetailActivity::class.java)
            intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, holder.destination!!.id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txvDestination: TextView = itemView.findViewById(R.id.txv_destination)
        var destination: Destination? = null

        override fun toString(): String {
            return """${super.toString()} '${txvDestination.text}'"""
        }
    }
}
