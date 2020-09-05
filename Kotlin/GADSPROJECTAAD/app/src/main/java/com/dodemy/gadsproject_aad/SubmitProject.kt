package com.dodemy.gadsproject_aad

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class SubmitProject : AppCompatActivity() {
    var firstName: EditText? = null
    var lastName: EditText? = null
    var projectLink: EditText? = null
    var email: EditText? = null
    var submit: Button? = null
    var progressDialog: ProgressDialog? = null
    var queue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_project)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = ""
        }
        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Loading...")
        email = findViewById(R.id.email)
        firstName = findViewById(R.id.edtName)
        lastName = findViewById(R.id.lastName)
        queue = Volley.newRequestQueue(applicationContext)
        projectLink = findViewById(R.id.link)
        submit = findViewById(R.id.submitBtn)
        submit!!.setOnClickListener { openDialog() }
    }

    //Posting data with Volley
    private fun postData(name: String, email: String, link: String, lastName: String) {
        progressDialog!!.show()
        val request: StringRequest = object : StringRequest(
            Method.POST,
            Constants.url,
            Response.Listener { response: String ->
                Log.d("TAG", "Response: $response")
                if (response.isNotEmpty()) {
                    successDialog()
                    this@SubmitProject.email!!.text = null
                    firstName!!.text = null
                    this.lastName!!.text = null
                    projectLink!!.text = null
                }
                progressDialog!!.dismiss()
            }, Response.ErrorListener {
                progressDialog!!.dismiss()
                failedDialog()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params[Constants.name] = name
                params[Constants.email] = email
                params[Constants.lastName] = lastName
                params[Constants.projectLink] = link
                return params
            }
        }
        request.retryPolicy = DefaultRetryPolicy(
            60000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue!!.add(request)
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.confirm)
        val yesDialog = dialog.findViewById<Button>(R.id.yes)
        val noDialog = dialog.findViewById<Button>(R.id.closeImg)
        noDialog.setOnClickListener { dialog.dismiss() }
        yesDialog.setOnClickListener { _: View? ->
            postData(
                firstName!!.text.toString().trim { it <= ' ' },
                email!!.text.toString().trim { it <= ' ' },
                projectLink!!.text.toString().trim { it <= ' ' },
                lastName!!.text.toString().trim { it <= ' ' })
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun successDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.sucess_popup)
        val closeDialog = dialog.findViewById<Button>(R.id.successImg)
        closeDialog.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun failedDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.failure_popup)
        val closeDialog = dialog.findViewById<Button>(R.id.failedImg)
        closeDialog.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}