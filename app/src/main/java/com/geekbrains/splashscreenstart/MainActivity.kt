package com.geekbrains.splashscreenstart

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.geekbrains.splashscreenstart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val screen = installSplashScreen() // запускаем splash screen
        setContentView(binding.root)

        /**
         * Создаём анимацию для splash screen
         */
        screen.setOnExitAnimationListener{ screenProvider ->
            ObjectAnimator.ofFloat(
                screenProvider.view,
                View.TRANSLATION_Y,
                0f,
                screenProvider.view.height.toFloat()
            ).apply {
                duration = 5000
                interpolator = AnticipateInterpolator()
                doOnEnd {
                    screenProvider.remove()
                }
            }.start()
        }

    }

}