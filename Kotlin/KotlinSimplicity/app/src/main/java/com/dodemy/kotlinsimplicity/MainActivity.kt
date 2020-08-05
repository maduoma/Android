package com.dodemy.kotlinsimplicity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.kotlinsimplicity.dao.IFoodDAO
import com.dodemy.kotlinsimplicity.dto.Food
import com.dodemy.kotlinsimplicity.dto.FoodType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var allFoods  = ArrayList<Food>()
    private var food = Food()
    private var _foodType: List<FoodType>? = ArrayList<FoodType>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            newFood()
        }

        actType.setOnItemClickListener { parent, view, position, id ->
            var selectedFoodType = parent.getItemAtPosition(position) as FoodType
            var foodTypeName = selectedFoodType.type
            Toast.makeText(this, " $foodTypeName  Very nutritious!", Toast.LENGTH_LONG).show()
        }

        btnSave.setOnClickListener {
            save()
        }

        spnFood.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                food = parent?.getItemAtPosition(position) as Food
                with(food) {
                    actType.setText(type)
                    edtCalories.setText(calories.toString())
                    edtCost.setText(cost)
                    edtName.setText(name.properCase())
                    edtPrepTime.setText(prepTime)
                }
            }

        }
        prepopulateFoods()
        updateSpinner()
        fetchFoodTypes()

    }

    private fun newFood() {
        food = Food();
        actType.setText("")
        edtCalories.setText("")
        edtCost.setText("")
        edtName.setText("")
        edtPrepTime.setText("")
    }

    private fun prepopulateFoods() {
        val mustard = Food("Mustard", 0, "1.29", "0")
        allFoods.add(mustard)
        val pickles = Food("Pickles")
        allFoods.add(pickles)
        val pawpaw = Food("Paw Paw", 100, "10.00")
        allFoods.add(pawpaw)
        val fujiApple = Food(name="Fuji Apple", cost="1.99")
        allFoods.add(fujiApple)
    }

    private fun save() {
        with (food) {
            name = edtName.text.toString()
            calories = if (edtCalories.text.toString().trim().isNotEmpty()) {
                Integer.parseInt(edtCalories.text.toString())
            } else {
                0
            }
            cost = edtCost.text.toString()
            prepTime = edtPrepTime.text.toString()
            type = actType.text.toString()
        }
        allFoods.add(food)
        food = Food()
    }

    private fun updateSpinner() {
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, allFoods)
        spnFood.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchFoodTypes() {

        var scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                val service = RetrofitClientInstance.retrofitInstance?.create(IFoodDAO::class.java)
                val receivedFoodType = async { service?.getFoodTypes() }
                _foodType =  receivedFoodType.await()
                val adapter = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item, _foodType!!)
                actType.setAdapter(adapter)
            }

    }

    fun String.properCase() : String {
        return if (this.length > 1) {
            this.substring(0,1).toUpperCase() + this.substring(1).toLowerCase()
        } else {
            this
        }
    }
}
