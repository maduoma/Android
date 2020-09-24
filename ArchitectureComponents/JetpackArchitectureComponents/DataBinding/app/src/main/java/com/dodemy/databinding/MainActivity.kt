package com.dodemy.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dodemy.databinding.databinding.ActivityMainBinding

/**
 * @author Maduabughichi Achilefu
 * Layout and Binding expressions
 * binding is the instance variable of ActivityMainBinding
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Replaces & sets setContentView(R.layout.activity_main) with Data Binding
        //ActivityMainBinding is created automatically from the XML layout file
        //If ActivityMainBinding is not showing as you type, rebuild and/or Invalidate/Restart Android Studio
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Binds views/widgets using Data Binding
        //name1 & email1 are the name variables in the XML file (activity_main.xml)
        //If name1 in getString(R.string.name1) is highlighted red, rebuild to make it disappear
        binding.name1.text = getString(R.string.name1)
        binding.email1.text = getString(R.string.email1)

        //Binds data objects class [Contact(var myName: String, var myEmail: String)] with views
        //contact is the name variable in the XML file (activity_main.xml)
        binding.contact = Contact("Maduabughichi Achilefu2", "email1@gmail.com2")

        //handler is the name variable in the XML file (activity_main.xml)
        //Event handling [open class EventHandler(context: Context)] with Data Binding
        binding.handler = EventHandler(this)

    }
}