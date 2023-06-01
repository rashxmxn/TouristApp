package com.example.touristapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.touristapp.databinding.ActivityMainBinding
import com.example.touristapp.databinding.ActivitySplashScreenBinding
import com.example.touristapp.databinding.FragmentItineraryBinding
import java.util.*
import kotlin.concurrent.timerTask

class SplashScreenActivity : AppCompatActivity() {
    //private lateinit var binding: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val splashScreenTimeOut = 3000
        val Intent = Intent(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(Intent)
            finish()
        }, splashScreenTimeOut.toLong())
    }

}