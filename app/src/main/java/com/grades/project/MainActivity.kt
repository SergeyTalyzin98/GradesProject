package com.grades.project

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.grades.project.worker.MyWorkRequestFactory
import com.grades.project.worker.SyncWithServerWorker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val coinView: View = findViewById(R.id.coin_view)

        val coinFlipAnimator: ValueAnimator = createCoinFlipAnimator(coinView)
        val coinSizeAnimator: ValueAnimator = createCoinSizeAnimator(coinView)
        val coinAnimatorSet: Animator = createCoinAnimatorSet(coinFlipAnimator, coinSizeAnimator)

        val coinAlphaAnimator: ObjectAnimator = createCoinAlphaAnimator(coinView)

        coinView.setOnClickListener {
            // Аниимация через ValueAnimator и AnimatorSet
            if (!coinAnimatorSet.isRunning) {
                val width: Int = it.measuredWidth
                coinSizeAnimator.setIntValues(width, width + 300, width)
                coinAnimatorSet.start()
            }

            // Аниимация через ObjectAnimator
            // if (!coinAlphaAnimator.isRunning) {
            // coinAlphaAnimator.start()
            // }

            // Анимация через ViewPropertyAnimator
            // coinView.animate().apply {
            // alpha(0F)
            // interpolator = AccelerateDecelerateInterpolator()
            // duration = 5000
            // }
        }
    }

    private fun createCoinFlipAnimator(coinView: View): ValueAnimator {
        return ValueAnimator().apply {
            setFloatValues(0F, 360F * 5)
            duration = 4000L
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                coinView.rotationX = animator.animatedValue as Float
            }
        }
    }

    private fun createCoinSizeAnimator(coinView: View): ValueAnimator {
        return ValueAnimator().apply {
            duration = 4000L
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                coinView.updateLayoutParams {
                    width = animator.animatedValue as Int
                    height = animator.animatedValue as Int
                }
            }
        }
    }

    private fun createCoinAnimatorSet(
        coinFlipAnimator: Animator,
        coinSizeAnimator: Animator,
    ): AnimatorSet {
        return AnimatorSet().apply {
            play(coinFlipAnimator).with(coinSizeAnimator)
        }
    }

    private fun createCoinAlphaAnimator(coinView: View): ObjectAnimator {
        return ObjectAnimator.ofFloat(coinView, View.ALPHA, 1F, 0.0F).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 10
            repeatMode = ValueAnimator.REVERSE
        }
    }
}