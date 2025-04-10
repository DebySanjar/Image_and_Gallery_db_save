package com.example.writeimgtodb.adapters

import android.app.Dialog
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.writeimgtodb.R
import com.example.writeimgtodb.databinding.ItemRecyBinding
import com.example.writeimgtodb.models.RecyItemModel

class MyRecyAdapter(private val list: List<RecyItemModel>) :
    RecyclerView.Adapter<MyRecyAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemRecyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRecyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.binding.name.text = item.name
        holder.binding.itemImg.setImageURI(Uri.parse(item.uriNme))

        holder.binding.itemImg.setOnClickListener {
            val dialog =
                Dialog(holder.itemView.context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_image_fullscreen)

            val fullImage = dialog.findViewById<ImageView>(R.id.full_img)
            val namez = dialog.findViewById<TextView>(R.id.full_name)
            fullImage.setImageURI(Uri.parse(item.uriNme))
            namez.setText(item.name.toString())
            fullImage.setOnClickListener { dialog.dismiss() }

            dialog.show()
        }

    }

    override fun getItemCount(): Int = list.size
}
