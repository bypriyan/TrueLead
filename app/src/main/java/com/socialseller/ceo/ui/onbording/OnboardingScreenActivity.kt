package com.socialseller.ceo.ui.onbording

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBoardingScreen
import com.bypriyan.sharemarketcourseinhindi.model.ModelOnBordingScreen
import com.socialseller.ceo.databinding.ActivityOnboardingScreenBinding
import com.socialseller.ceo.ui.auth.AuthActivity
import com.socialseller.clothcrew.utility.MyActivity

class OnboardingScreenActivity : MyActivity() {

    private val binding by lazy { ActivityOnboardingScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

// Set adapter to ViewPager2
        var adapter = AdapterOnBoardingScreen(this, getListOfOnBordingScreenContent())
        binding.viewPager2.adapter = adapter

        binding.wormDotsIndicator.attachTo(binding.viewPager2)

        binding.viewPager2.isUserInputEnabled = false

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        binding.previousBtn.visibility = View.GONE
                        binding.nextBtn.visibility = View.VISIBLE
                        binding.nextBtn.text = "NEXT"

                    }

                    1 -> {
                        binding.previousBtn.visibility = View.VISIBLE
                        binding.nextBtn.visibility = View.VISIBLE
                        binding.nextBtn.text = "NEXT"

                    }

                    2 -> {
                        binding.previousBtn.visibility = View.VISIBLE
                        binding.nextBtn.text = "Continue"
                    }

                    else -> binding.previousBtn.visibility = View.GONE
                }
            }
        })

        binding.nextBtn.setOnClickListener {
            val nextIndex = binding.viewPager2.currentItem + 1
            if (nextIndex < adapter.itemCount) {
                binding.viewPager2.currentItem = nextIndex
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }



        binding.previousBtn.setOnClickListener {
            val previousIndex = binding.viewPager2.currentItem - 1
            if (previousIndex >= 0) {
                binding.viewPager2.currentItem = previousIndex
            }
        }

    }

    fun getListOfOnBordingScreenContent(): List<ModelOnBordingScreen> {
        return listOf(
            ModelOnBordingScreen(
                "https://img.freepik.com/free-vector/crm-concept-illustration_114360-1503.jpg",
                "Effortless Lead Management",
                "Easily track and manage your assigned leads with a streamlined workflow."
            ),

            ModelOnBordingScreen(
                "https://img.freepik.com/free-vector/generating-new-leads-concept-illustration_114360-7654.jpg",
                "Track Lead Progress",
                "Update lead stages in real-time and keep everyone in the loop."
            ),

            ModelOnBordingScreen(
                "https://img.freepik.com/free-vector/push-notifications-concept-illustration_114360-4986.jpg",
                "Instant Notifications",
                "Receive instant updates and reminders for follow-ups and lead actions."
            ),

            ModelOnBordingScreen(
                "https://linkurious.com/images/uploads/2024/06/Illustration-for-Gartner-summary-blog-post.jpg",
                "Data-Driven Insights",
                "Analyze your sales performance with detailed reports and analytics."
            )


        )
    }
}