package com.socialseller.ceo.ui.onbording

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.socialseller.clothcrew.utility.MyActivity

class SplashScreenActivity : MyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, OnboardingScreenActivity::class.java))
        finish()

    }
}