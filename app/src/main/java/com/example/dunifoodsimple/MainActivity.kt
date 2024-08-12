package com.example.dunifoodsimple

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifoodsimple.databinding.ActivityMainBinding
import com.example.dunifoodsimple.databinding.DialogAddNewItemBinding
import com.example.dunifoodsimple.databinding.DialogDeleteItemBinding
import com.example.dunifoodsimple.databinding.DialogUpdateItemBinding
import com.example.dunifoodsimple.room.Food
import com.example.dunifoodsimple.room.FoodDao
import com.example.dunifoodsimple.room.MyDatabase

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: FoodAdapter
    lateinit var foodList: ArrayList<Food>
    lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodDao = MyDatabase.getDatabase(this).foodDao

        val sharedPreferences = getSharedPreferences("duniFood", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run", true)) {
            firstRun()

            sharedPreferences.edit().putBoolean("first_run", false).apply()
        }

        showAllData()

//        binding.btnAddNewFood.setOnClickListener {
//
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            dialogBinding.dialogBtnDone.setOnClickListener {
//
//                if (
//                    dialogBinding.dialogEdtNameFood.length() > 0 &&
//                    dialogBinding.dialogEdtFoodPrice.length() > 0 &&
//                    dialogBinding.dialogEdtFoodCity.length() > 0 &&
//                    dialogBinding.dialogEdtFoodDistance.length() > 0
//                ) {
//                    val textName = dialogBinding.dialogEdtNameFood.text.toString()
//                    val textPrice = dialogBinding.dialogEdtFoodPrice.text.toString()
//                    val textCity = dialogBinding.dialogEdtFoodCity.text.toString()
//                    val textDistance = dialogBinding.dialogEdtFoodDistance.text.toString()
//                    val textRatingNumber: Int = (1..150).random()
//                    val ratingBarStar: Float = (1..5).random().toFloat()
//                    val randomForUrl = (0..foodList.size - 1).random()
//                    val urlPic = foodList[randomForUrl].urlImage
//
//                    val newFood = Food(
//                        textName,
//                        textPrice,
//                        textDistance,
//                        textCity,
//                        urlPic,
//                        textRatingNumber,
//                        ratingBarStar
//                    )
//                    myAdapter.addFood(newFood)
//                    dialog.dismiss()
//                    binding.RVMain.scrollToPosition(0)
//                } else {
//                    Toast.makeText(this, "لطفا مقادیر را وارد کنید.", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        }
//
//        binding.edtSearch.addTextChangedListener { editTextInput ->
//
//            if (editTextInput!!.isNotEmpty()) {
//                // Filter data:
//                val cloneList = foodList.clone() as ArrayList<Food>
//                val filteredList = cloneList.filter { foodItem ->
//                    foodItem.txtSubject.contains(editTextInput)
//                }
//                myAdapter.setData(ArrayList(filteredList))
//
//            } else {
//                // Show all data:
//                myAdapter.setData(foodList.clone() as ArrayList<Food>)
//            }
//
//        }

    }

    private fun firstRun() {
        foodList = arrayListOf(
            Food(
                txtSubject = "Hamburger",
                txtPrice = "15",
                txtDistance = "3",
                txtCity = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                numOfRating = 20,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Grilled fish",
                txtPrice = "20",
                txtDistance = "2.1",
                txtCity = "Tehran, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                numOfRating = 10,
                rating = 4f
            ),
            Food(
                txtSubject = "Lasania",
                txtPrice = "40",
                txtDistance = "1.4",
                txtCity = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                numOfRating = 30,
                rating = 2f
            ),
            Food(
                txtSubject = "pizza",
                txtPrice = "10",
                txtDistance = "2.5",
                txtCity = "Zahedan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                numOfRating = 80,
                rating = 1.5f
            ),
            Food(
                txtSubject = "Sushi",
                txtPrice = "20",
                txtDistance = "3.2",
                txtCity = "Mashhad, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                numOfRating = 200,
                rating = 3f
            ),
            Food(
                txtSubject = "Roasted Fish",
                txtPrice = "40",
                txtDistance = "3.7",
                txtCity = "Jolfa, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                numOfRating = 50,
                rating = 3.5f
            ),
            Food(
                txtSubject = "Fried chicken",
                txtPrice = "70",
                txtDistance = "3.5",
                txtCity = "NewYork, USA",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                numOfRating = 70,
                rating = 2.5f
            ),
            Food(
                txtSubject = "Vegetable salad",
                txtPrice = "12",
                txtDistance = "3.6",
                txtCity = "Berlin, Germany",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                numOfRating = 40,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Grilled chicken",
                txtPrice = "10",
                txtDistance = "3.7",
                txtCity = "Beijing, China",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                numOfRating = 15,
                rating = 5f
            ),
            Food(
                txtSubject = "Baryooni",
                txtPrice = "16",
                txtDistance = "10",
                txtCity = "Ilam, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                numOfRating = 28,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Ghorme Sabzi",
                txtPrice = "11.5",
                txtDistance = "7.5",
                txtCity = "Karaj, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                numOfRating = 27,
                rating = 5f
            ),
            Food(
                txtSubject = "Rice",
                txtPrice = "12.5",
                txtDistance = "2.4",
                txtCity = "Shiraz, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                numOfRating = 35,
                rating = 2.5f
            ),
        )
        foodDao.insertAllFood(foodList)
    }

    private fun showAllData() {

        val foodData = foodDao.getAllFoods()

        myAdapter = FoodAdapter(ArrayList(foodData), this)
        binding.RVMain.adapter = myAdapter
        binding.RVMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    }

    override fun onFoodClicked(food: Food, position: Int) {

//        val dialog = AlertDialog.Builder(this).create()
//        val dialogUpdateBinding = DialogUpdateItemBinding.inflate(layoutInflater)
//        dialog.setView(dialogUpdateBinding.root)
//        dialog.setCancelable(false)
//        dialog.show()
//
//        dialogUpdateBinding.dialogEdtNameFood.setText(food.txtSubject)
//        dialogUpdateBinding.dialogEdtFoodCity.setText(food.txtCity)
//        dialogUpdateBinding.dialogEdtFoodPrice.setText(food.txtPrice)
//        dialogUpdateBinding.dialogEdtFoodDistance.setText(food.txtDistance)
//
//        dialogUpdateBinding.dialogUpdateBtnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogUpdateBinding.dialogUpdateBtnDone.setOnClickListener {
//            if (
//                dialogUpdateBinding.dialogEdtNameFood.length() > 0 &&
//                dialogUpdateBinding.dialogEdtFoodPrice.length() > 0 &&
//                dialogUpdateBinding.dialogEdtFoodCity.length() > 0 &&
//                dialogUpdateBinding.dialogEdtFoodDistance.length() > 0
//            ) {
//                val textName = dialogUpdateBinding.dialogEdtNameFood.text.toString()
//                val textPrice = dialogUpdateBinding.dialogEdtFoodPrice.text.toString()
//                val textCity = dialogUpdateBinding.dialogEdtFoodCity.text.toString()
//                val textDistance = dialogUpdateBinding.dialogEdtFoodDistance.text.toString()
//                val newFood = Food(
//                    textName,
//                    textPrice,
//                    textDistance,
//                    textCity,
//                    food.urlImage,
//                    food.numOfRating,
//                    food.rating
//                )
//
//                myAdapter.updateFood(newFood, position)
//                dialog.dismiss()
//            } else {
//                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید.", Toast.LENGTH_SHORT).show()
//            }
//
//
//        }
    }

    override fun onFoodLongClicked(food: Food, pos: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)
//        dialog.setView(dialogDeleteBinding.root)
//        dialog.setCancelable(true)
//        dialog.show()
//
//
//        dialogDeleteBinding.dialogDeleteBtnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogDeleteBinding.dialogDeleteBtnSure.setOnClickListener {
//            dialog.dismiss()
//            myAdapter.removeFood(food, pos)
//        }
    }
}
