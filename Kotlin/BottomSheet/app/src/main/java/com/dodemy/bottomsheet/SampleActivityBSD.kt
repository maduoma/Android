package com.dodemy.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main.*

class SampleActivity :AppCompatActivity(),OptionsBottomSheetFragment.ItemClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.amin)
        tv_click_me?.setOnClickListener {
            supportFragmentManager.let {
                OptionsBottomSheetFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }
        }
    }

    override fun onItemClick(item:String) {
        when(item){
            "share"->{
                //Handle data
            }
            "Download"->{
                //Handle data
            }
            else->{
                //Handle data
            }
        }
    }

}