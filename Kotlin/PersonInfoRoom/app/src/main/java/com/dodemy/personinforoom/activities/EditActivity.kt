package com.dodemy.personinforoom.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.personinforoom.R
import com.dodemy.personinforoom.constants.Constants
import com.dodemy.personinforoom.database.AppDatabase
import com.dodemy.personinforoom.database.AppExecutors
import com.dodemy.personinforoom.model.Person


class EditActivity : AppCompatActivity() {
    var name: EditText? = null
    var email: EditText? = null
    var pincode: EditText? = null
    var city: EditText? = null
    var phoneNumber: EditText? = null
    var button: Button? = null
    var mPersonId = 0
    //var intent: Intent? = null
    private var mDb: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
        initViews()
        mDb = AppDatabase.getInstance(applicationContext)
        intent = intent
        if (intent != null && intent!!.hasExtra(Constants.UPDATE_Person_Id)) {
            button?.text = getString(R.string.update)
            mPersonId = intent!!.getIntExtra(Constants.UPDATE_Person_Id, -1)
            AppExecutors.instance!!.diskIO().execute {
                val person: Person = mDb?.personDao()?.loadPersonById(mPersonId)!!
                populateUI(person)
            }
        }
    }

    private fun populateUI(person: Person?) {
        if (person == null) {
            return
        }
        name?.text.toString()
        email?.text.toString()
        phoneNumber!!.text.toString()
        pincode!!.text.toString()
        city!!.text.toString()
    }

    private fun initViews() {
        name = findViewById(R.id.edit_name)
        email = findViewById(R.id.edit_email)
        pincode = findViewById(R.id.edit_pincode)
        city = findViewById(R.id.edit_city)
        phoneNumber = findViewById(R.id.edit_number)
        button = findViewById(R.id.button)
        button?.setOnClickListener {
            onSaveButtonClicked()
        }
    }

    private fun onSaveButtonClicked() {
        val person = Person(
            name!!.text.toString(),
            email!!.text.toString(),
            phoneNumber!!.text.toString(),
            pincode!!.text.toString(),
            city!!.text.toString()
        )
        AppExecutors.instance?.diskIO()?.execute(Runnable {
            if (!intent!!.hasExtra(Constants.UPDATE_Person_Id)) {
                mDb?.personDao()?.insertPerson(person)
            } else {
                person.id = mPersonId
                mDb?.personDao()?.updatePerson(person)
            }
            finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
