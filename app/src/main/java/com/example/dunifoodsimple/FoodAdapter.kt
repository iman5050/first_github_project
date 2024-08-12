package com.example.dunifoodsimple

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifoodsimple.room.Food
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvents: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val txtSubject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val txtCity = itemView.findViewById<TextView>(R.id.item_txt_city)
        val txtprice = itemView.findViewById<TextView>(R.id.item_txt_price)
        val txtDistance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.item_RB_main)
        val txtRating = itemView.findViewById<TextView>(R.id.item_txt_rating)

        fun bindData(position: Int) {

            txtSubject.text = data[position].txtSubject
            txtCity.text = data[position].txtCity
            txtprice.text = buildString {
                append("$ ")
                append(data[position].txtPrice)
                append(" , VIP")
            }
            txtDistance.text = buildString {
                append(data[position].txtDistance)
                append(" miles from you")
            }
            ratingBar.rating = data[position].rating
            txtRating.text = buildString {
                append("(")
                append(data[position].numOfRating.toString())
                append(" Ratings")
                append(")")
            }

            Glide
                .with(context)
                .load(data[position].urlImage)
                .transform(RoundedCornersTransformation(16, 4))
                .into(imgMain)

            itemView.setOnClickListener {
                foodEvents.onFoodClicked(data[adapterPosition], adapterPosition)
            }
            itemView.setOnLongClickListener {
                foodEvents.onFoodLongClicked(data[adapterPosition], adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.bindData(position)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addFood(newFood: Food) {
        data.add(0, newFood)
        notifyItemInserted(0)
    }
    fun removeFood(oldFood: Food, oldPosition: Int) {

        data.remove(oldFood)
        notifyItemRemoved(oldPosition)

    }
    fun updateFood(updateFood: Food, position: Int) {
        data[position] = updateFood
        notifyItemChanged(position)
    }
    fun setData(newList: ArrayList<Food>){
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface FoodEvents {

        fun onFoodClicked(food: Food, position: Int)
        fun onFoodLongClicked(food: Food, pos: Int)
    }

}


