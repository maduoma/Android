/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dodemy.mvvm_creaturemon.view.creature

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dodemy.mvvm_creaturemon.R
import com.dodemy.mvvm_creaturemon.databinding.ActivityCreatureBinding
import com.dodemy.mvvm_creaturemon.model.AttributeStore
import com.dodemy.mvvm_creaturemon.model.AttributeType
import com.dodemy.mvvm_creaturemon.model.AttributeValue
import com.dodemy.mvvm_creaturemon.model.Avatar
import com.dodemy.mvvm_creaturemon.view.avatars.AvatarAdapter
import com.dodemy.mvvm_creaturemon.view.avatars.AvatarBottomDialogFragment
import com.dodemy.mvvm_creaturemon.viewmodel.CreatureViewModel
import kotlinx.android.synthetic.main.activity_creature.*


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

  private lateinit var viewModel: CreatureViewModel

  lateinit var binding: ActivityCreatureBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_creature)

    viewModel = ViewModelProviders.of(this).get(CreatureViewModel::class.java)
    binding.viewmodel = viewModel

    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureClickListeners()
    configureLiveDataObservers()
  }

  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)

    if(viewModel.drawable != 0){
      hideTapLabel()
    }
  }

  private fun configureSpinnerAdapters() {
    intelligenceSpinner.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
    strengthSpinner.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
    enduranceSpinner.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
  }

  private fun configureSpinnerListeners() {
    intelligenceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.INTELLIGENCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    strengthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.STRENGTH, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    enduranceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.ENDURANCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureClickListeners() {
    avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }
  }

  private fun configureLiveDataObservers(){
    viewModel.getCreatureLiveData().observe(this, Observer { creature ->
      creature?.let {
        hitPoints.text = it.hitPoints.toString()
        avatarImageView.setImageResource(it.drawable)
        nameEditText.setText(it.name)
      }
    })
    viewModel.getSaveLiveData().observe(this, Observer { saved ->
      saved?.let {
        if(saved){
          Toast.makeText(this, getString(R.string.creature_saved), Toast.LENGTH_LONG).show()
          finish()
        }else {
          Toast.makeText(this, getString(R.string.error_saving_creature), Toast.LENGTH_LONG).show()
        }
      }
    })
  }

  override fun avatarClicked(avatar: Avatar) {
    viewModel.drawableSelected(avatar.drawable)
    hideTapLabel()
  }

  private fun hideTapLabel() {
    tapLabel.visibility = View.INVISIBLE
  }
}
