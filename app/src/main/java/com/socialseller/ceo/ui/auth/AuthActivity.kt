package com.socialseller.ceo.ui.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.ActivityAuthBinding
import com.socialseller.ceo.databinding.ActivityOnboardingScreenBinding
import com.socialseller.clothcrew.utility.MyActivity

class AuthActivity : MyActivity() {

    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}