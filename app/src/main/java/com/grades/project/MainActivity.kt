package com.grades.project

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.grades.project.dialog.MyDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        MyDialog(this).show()

        val image: View = findViewById(R.id.coin_view)

        val coinFlipAnimator = ValueAnimator().apply {
            setFloatValues(0F, 360F * 5)
            duration = 4000L
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                image.rotationX = animator.animatedValue as Float
            }
        }
        val coinSizeAnimator = ValueAnimator().apply {
            duration = 4000L
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                image.updateLayoutParams {
                    width = animator.animatedValue as Int
                    height = animator.animatedValue as Int
                }
            }
        }
        val coinAnimatorSet = AnimatorSet().apply {
            play(coinFlipAnimator).with(coinSizeAnimator)
        }


        val coinAlphaAnimator = ObjectAnimator.ofFloat(image, View.ALPHA, 1F, 0.0F).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 10
            repeatMode = ValueAnimator.REVERSE
        }

        image.setOnClickListener {
            // Аниимация через ValueAnimator и AnimatorSet
            if (!coinAnimatorSet.isRunning) {
                val width: Int = it.measuredWidth
                coinSizeAnimator.setIntValues(width, width + 300, width)
                coinAnimatorSet.start()
            }

            // Аниимация через ObjectAnimator
//            if (!coinAlphaAnimator.isRunning) {
//                coinAlphaAnimator.start()
//            }

            // Анимация через ViewPropertyAnimator
            image.animate().apply {
                alpha(0F)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 5000
            }
        }
    }

    private fun createWork() {
        WorkManager.getInstance(this)
            .enqueueUniqueWork(
                SyncWithServerWorker.NAME,
                ExistingWorkPolicy.REPLACE,
                MyWorkRequestFactory.create("some data"),
            )
    }
}