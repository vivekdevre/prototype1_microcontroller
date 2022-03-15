package com.example.prototype1
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ReadDataAdapter(private val listener: ReadItemClicked): RecyclerView.Adapter<DataViewHolder>() {
    private val items: ArrayList<ReadD> = ArrayList()


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = items[position]
        holder.id1.text = currentItem.id
        holder.name.text = currentItem.name

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<ReadD>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_readdata,parent,false)
        val viewHolder = DataViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }
}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id1: TextView = itemView.findViewById(R.id.id)
    val name: TextView = itemView.findViewById(R.id.textView3)

}

interface ReadItemClicked{
    fun onItemClicked(item: ReadD)
}