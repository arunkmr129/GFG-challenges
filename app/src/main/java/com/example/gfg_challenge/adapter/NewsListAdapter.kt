package com.example.gfg_challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gfg_challenge.R
import com.example.gfg_challenge.model.Items
import java.text.SimpleDateFormat


class NewsListAdapter(val context: Context, var list: ArrayList<Items>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HOR = 1
        const val VIEW_TYPE_SQU = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SQU) {
            return SquareViewHolder(
                LayoutInflater.from(context).inflate(R.layout.square_row_item, parent, false)
            )
        }
        return HorizontalViewHolder(
            LayoutInflater.from(context).inflate(R.layout.horizontal_row_item, parent, false)
        )
    }

    fun updateList(updatedList: ArrayList<Items>) {
        list = updatedList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val items = list[position]
        if (holder is HorizontalViewHolder) {
            holder.onBind(items)
        } else if (holder is SquareViewHolder) {
            holder.onBind(items)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_SQU else VIEW_TYPE_HOR
    }

    inner class HorizontalViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPbDate: TextView = itemView.findViewById(R.id.tvPbDate)
        val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        fun onBind(items: Items) {
            setData(tvTitle, tvPbDate, items)
            loadImage(ivThumbnail, items.thumbnail!!)
        }
    }

    inner class SquareViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPbDate: TextView = itemView.findViewById(R.id.tvPbDate)
        fun onBind(items: Items) {
            setData(tvTitle, tvPbDate, items)
            loadImage(ivThumbnail, items.enclosure!!.thumbnail!!)
        }
    }

    fun loadImage(imageView: ImageView, url: String) {
        val newImgUrl = url.replace("&amp;", "&");
        Glide
            .with(context)
            .load(newImgUrl)
            .into(imageView);
    }

    fun setData(tvTitle: TextView, tvPbDate: TextView, items: Items) {
        tvTitle.text = items.title
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm a")
        val formattedDate = formatter.format(parser.parse(items.pubDate))
        tvPbDate.text = formattedDate
    }

}