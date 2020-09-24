package com.dodemy.gadsproject_aad

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.dodemy.gadsproject_aad.tabs.SkillFragment
import com.dodemy.gadsproject_aad.tabs.TopLearnerFragment
import com.google.android.material.tabs.TabLayout
import kotlin.math.max

open class MainActivity : AppCompatActivity() {

    private val extraCircularRevealX = "EXTRA_CIRCULAR_REVEAL_X"
    private val extraCircularRevealY = "EXTRA_CIRCULAR_REVEAL_Y"
    var rootLayout: View? = null
    private var revealX = 0
    private var revealY = 0


    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    var submit: TextView? = null
    var skillFragment: SkillFragment? = null
    var topLearnerFragment: TopLearnerFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        skillFragment = SkillFragment()
        topLearnerFragment = TopLearnerFragment()
        submit = findViewById(R.id.submit)
        submit!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    SubmitProject::class.java
                )
            )
        })
        val myPagerAdapter: MyPagerAdapter = MyPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        tabLayout!!.setupWithViewPager(viewPager)
        viewPager!!.adapter = myPagerAdapter
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {}
            override fun onPageScrollStateChanged(i: Int) {}
        })

        val intent = intent
        rootLayout = findViewById(R.id.root_layout)
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(this@MainActivity.extraCircularRevealX) &&
            intent.hasExtra(this@MainActivity.extraCircularRevealY)
        ) {
            rootLayout!!.visibility = View.INVISIBLE
            revealX = intent.getIntExtra(this@MainActivity.extraCircularRevealX, 0)
            revealY = intent.getIntExtra(this@MainActivity.extraCircularRevealY, 0)
            val viewTreeObserver = rootLayout!!.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            rootLayout!!.visibility = View.VISIBLE
        }


    }


    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (max(rootLayout!!.width, rootLayout!!.height) * 1.1).toFloat()
            // create the animator for this view (the start radius is zero)
            val circularReveal =
                ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
            circularReveal.duration = 400
            circularReveal.interpolator = AccelerateInterpolator()
            // make the view visible and start the animation
            rootLayout!!.visibility = View.VISIBLE
            circularReveal.start()
        } else {
            finish()
        }
    }


    internal inner class MyPagerAdapter(fm: FragmentManager?, behavior: Int) : FragmentPagerAdapter(
        fm!!,
        behavior
    ) {
        var fragmentNames = arrayOf(" Learning Leader ", "Skill IQ Leader")
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return topLearnerFragment!!
                1 -> return skillFragment!!
            }
            return null!!
        }

        override fun getCount(): Int {
            return fragmentNames.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentNames[position]
        }
    }
}