package com.smu.leisure

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smu.leisure.base.MyApplication
import com.smu.leisure.databinding.ItemDataBinding
import com.smu.leisure.db.LeisureEntity

class DataRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<LeisureEntity>, private val listener : OnItemClickListener) : RecyclerView.Adapter<DataRecyclerViewAdapter.MyViewHolder>()  {
    inner class MyViewHolder(binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvTitle = binding.tvTitle
        val tvEmergency = binding.tvEmergency

        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : ItemDataBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val leisureData = dataList[position]

        holder.tvTitle.text = leisureData.createddate

        if(leisureData.emergency == "D") {
            val strEmergency = context.getString(R.string.str_danger)
            holder.tvEmergency.text = strEmergency
        } else {
            val strEmergency = context.getString(R.string.str_critical)
            holder.tvEmergency.text = strEmergency
        }


        holder.root.setOnClickListener {
            listener.onDataItemClick(position)
            false
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}