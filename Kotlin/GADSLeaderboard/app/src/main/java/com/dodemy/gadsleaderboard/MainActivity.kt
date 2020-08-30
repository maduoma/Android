package com.dodemy.gadsleaderboard

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.dodemy.gadsleaderboard.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var signUpResponsesData: SignUpResponse? = null
    var emailId: EditText? = null
    var password: EditText? = null
    var name: EditText? = null
    var signUp: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        // init the EditText and Button
        // init the EditText and Button
        name = findViewById<View>(R.id.username) as EditText
        emailId = findViewById<View>(R.id.email) as EditText
        password = findViewById<View>(R.id.password) as EditText
        signUp = findViewById<View>(R.id.signUp) as Button
        // implement setOnClickListener event on sign up Button
        // implement setOnClickListener event on sign up Button
        signUp!!.setOnClickListener(View.OnClickListener { // validate the fields and call sign method to implement the api
            if (validate(name!!) && validateEmail() && validate(password!!)) {
                signUp()
            }
        })
    }

    private fun validateEmail(): Boolean {
        val email = emailId!!.text.toString().trim { it <= ' ' }
        if (email.isEmpty() || !isValidEmail(email)) {
            emailId!!.error = "Email is not valid."
            emailId!!.requestFocus()
            return false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validate(editText: EditText): Boolean {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            return true // returns true if field is not empty
        }
        editText.error = "Please Fill This"
        editText.requestFocus()
        return false
    }

    private fun signUp() {
        // display a progress dialog
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setCancelable(false) // set cancelable to false
        progressDialog.setMessage("Please Wait") // set message
        progressDialog.show() // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error

        /*
        (Api.getClient().registration(name.getText().toString().trim(),
                emailId.getText().toString().trim(),
                password.getText().toString().trim(),
                "email"))
         */



        API.getClient().registration(
            name!!.text.toString().trim { it <= ' ' },
            emailId!!.text.toString().trim { it <= ' ' },
            password!!.text.toString().trim { it <= ' ' },
            "email"
        ).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                signUpResponsesData = response.body()
                Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_SHORT)
                    .show()
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("response", t.stackTrace.toString())
                progressDialog.dismiss()
            }
        })
    }
}