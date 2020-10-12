package com.dodemy.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main.*

class SampleActivity :AppCompatActivity(),OptionsBottomSheetFragment.ItemClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
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