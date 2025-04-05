package com.socialseller.ceo.ui.onbording

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.ceo.ui.auth.AuthActivity
import com.socialseller.ceo.ui.home.HomeActivity
import com.socialseller.clothcrew.utility.MyActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : MyActivity() {

    @Inject
    lateinit var dataStore: DataStoreManager
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            dataStore.bearerTokenFlow.firstOrNull().let { token ->
                if (token.isNullOrEmpty()) {
                    startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                }
                splashScreen.setKeepOnScreenCondition { false }
                finish()
            }
        }

    }
}