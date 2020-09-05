package com.dodemy.gadsleaderboardaad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.dodemy.gadsleaderboardaad.R
import com.dodemy.gadsleaderboardaad.databinding.LeaderBoardFragmentBinding
import com.dodemy.gadsleaderboardaad.ui.learningLeaders.LearningLeadersFragment
import com.dodemy.gadsleaderboardaad.ui.skillLeaders.SkillLeaderFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import kotlin.math.max


@AndroidEntryPoint
class LeaderBoardFragment : Fragment() {

    lateinit var binding: LeaderBoardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LeaderBoardFragmentBinding.inflate(inflater, container, false)
        binding.pager.adapter = LeaderBoardAdapter(this)
        binding.pager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.learning_leaders)
                }
                else -> tab.text = getString(R.string.skill_iq_leaders)
            }
        }.attach()
        return binding.root
    }

    class LeaderBoardAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> LearningLeadersFragment()
                else -> SkillLeaderFragment()
            }
        }

    }

}

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}