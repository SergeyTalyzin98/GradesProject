package com.grades.project

import android.os.Build
import android.view.View
import android.view.WindowManager

class P {

//    @Suppress("DEPRECATION")
//    private fun showSystemItemsVisibilityState(state: MyDialogSystemItemsVisibilityState) {
//        when (state) {
//            is MyDialogSystemItemsVisibilityState.AllHidden -> {
//                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        )
//                window.setFlags(
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN
//                )
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    window.attributes = window.attributes.apply {
//                        layoutInDisplayCutoutMode =
//                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//                    }
//                }
//            }
//            is MyDialogSystemItemsVisibilityState.AllVisible -> {
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    window.attributes = window.attributes.apply {
//                        layoutInDisplayCutoutMode =
//                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
//                    }
//                }
//            }
//            is MyDialogSystemItemsVisibilityState.NavigationBarVisible -> {
//                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//                window.setFlags(
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN
//                )
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    window.attributes = window.attributes.apply {
//                        layoutInDisplayCutoutMode =
//                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//                    }
//                }
//            }
//            is MyDialogSystemItemsVisibilityState.StatusBarVisible -> {
//                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    window.attributes = window.attributes.apply {
//                        layoutInDisplayCutoutMode =
//                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
//                    }
//                }
//            }
//        }
//    }
}