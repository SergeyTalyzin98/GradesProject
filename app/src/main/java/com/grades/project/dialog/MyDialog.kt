package com.grades.project.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.view.ActionMode
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.SearchEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import com.grades.project.R

class MyDialog(
    private val context: Context,
) : Window.Callback {

    private val window: Window
    private val windowManager: WindowManager

    private val tapDetector = GestureDetector(
        context,
        object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                showSystemItemsVisibilityState(state)
                return true
            }
        },
    )

    private var state: MyDialogSystemItemsVisibilityState = MyDialogSystemItemsVisibilityState.AllVisible

    private var decorView: View? = null
    private var showing: Boolean = false

    init {
        window = createWindow()
        windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager

        window.callback = this
        window.setWindowManager(windowManager, null, null)

        window.setContentView(R.layout.dialog_layout)

        val allVisible: Button = window.decorView.findViewById(R.id.all_visible)
        val allHidden: Button = window.decorView.findViewById(R.id.all_hidden)
        val statusBarVisible: Button = window.decorView.findViewById(R.id.status_bar_visible)
        val navBarVisible: Button = window.decorView.findViewById(R.id.nav_bar_visible)

        allVisible.setOnClickListener {
            showSystemItemsVisibilityState(MyDialogSystemItemsVisibilityState.AllVisible)
        }
        allHidden.setOnClickListener {
            showSystemItemsVisibilityState(MyDialogSystemItemsVisibilityState.AllHidden)
        }
        statusBarVisible.setOnClickListener {
            showSystemItemsVisibilityState(MyDialogSystemItemsVisibilityState.StatusBarVisible)
        }
        navBarVisible.setOnClickListener {
            showSystemItemsVisibilityState(MyDialogSystemItemsVisibilityState.NavigationBarVisible)
        }
    }

    fun show() {
        if (showing) {
            return
        }
        decorView = window.decorView
        val config: Configuration = context.resources.configuration
        decorView?.dispatchConfigurationChanged(config)

        val layoutParams: WindowManager.LayoutParams = window.attributes
        windowManager.addView(decorView, layoutParams)
        showing = true
    }

    fun hide() {
        decorView?.visibility = View.GONE
    }

    @Suppress("DEPRECATION")
    private fun showSystemItemsVisibilityState(state: MyDialogSystemItemsVisibilityState) {
        when (state) {
            is MyDialogSystemItemsVisibilityState.AllHidden -> {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        )
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.attributes = window.attributes.apply {
                        layoutInDisplayCutoutMode =
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                    }
                }
            }
            is MyDialogSystemItemsVisibilityState.AllVisible -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.attributes = window.attributes.apply {
                        layoutInDisplayCutoutMode =
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
                    }
                }
            }
            is MyDialogSystemItemsVisibilityState.NavigationBarVisible -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.attributes = window.attributes.apply {
                        layoutInDisplayCutoutMode =
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                    }
                }
            }
            is MyDialogSystemItemsVisibilityState.StatusBarVisible -> {
                window.decorView.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.attributes = window.attributes.apply {
                        layoutInDisplayCutoutMode =
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
                    }
                }
            }
        }
    }

    @SuppressLint("PrivateApi")
    private fun createWindow(): Window {
        val className = "com.android.internal.policy.PhoneWindow"
        return Class.forName(className).constructors.first().newInstance(context) as Window
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d("MyDialog", "dispatchKeyEvent")
        window.superDispatchKeyEvent(event)
        return false
    }

    override fun dispatchKeyShortcutEvent(event: KeyEvent?): Boolean {
        Log.d("MyDialog", "dispatchKeyShortcutEvent")
        window.superDispatchKeyShortcutEvent(event)
        return false
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.d("MyDialog", "dispatchTouchEvent")
        tapDetector.onTouchEvent(event)
        return window.superDispatchTouchEvent(event)
    }

    override fun dispatchTrackballEvent(event: MotionEvent?): Boolean {
        Log.d("MyDialog", "dispatchTrackballEvent")
        window.superDispatchTrackballEvent(event)
        return false
    }

    override fun dispatchGenericMotionEvent(event: MotionEvent?): Boolean {
        Log.d("MyDialog", "dispatchGenericMotionEvent")
        window.superDispatchGenericMotionEvent(event)
        return false
    }

    override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent?): Boolean {
        Log.d("MyDialog", "dispatchPopulateAccessibilityEvent")
        return false
    }

    override fun onCreatePanelView(featureId: Int): View? {
        Log.d("MyDialog", "onCreatePanelView")
        return null
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
        Log.d("MyDialog", "onCreatePanelMenu")
        return false
    }

    override fun onPreparePanel(featureId: Int, view: View?, menu: Menu): Boolean {
        Log.d("MyDialog", "onPreparePanel")
        return false
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        Log.d("MyDialog", "onMenuOpened")
        return false
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        Log.d("MyDialog", "onMenuItemSelected")
        return false
    }

    override fun onWindowAttributesChanged(attrs: WindowManager.LayoutParams) {
        if (decorView != null) {
            windowManager.updateViewLayout(decorView, attrs)
        }
    }

    override fun onContentChanged() {
        Log.d("MyDialog", "onContentChanged")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        Log.d("MyDialog", "onWindowFocusChanged")
    }

    override fun onAttachedToWindow() {
        Log.d("MyDialog", "onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        Log.d("MyDialog", "onDetachedFromWindow")
    }

    override fun onPanelClosed(featureId: Int, menu: Menu) {
        Log.d("MyDialog", "onPanelClosed")
    }

    override fun onSearchRequested(): Boolean {
        Log.d("MyDialog", "onSearchRequested")
        return false
    }

    override fun onSearchRequested(searchEvent: SearchEvent?): Boolean {
        Log.d("MyDialog", "onSearchRequested")
        return false
    }

    override fun onWindowStartingActionMode(callback: ActionMode.Callback?): ActionMode? {
        Log.d("MyDialog", "onWindowStartingActionMode")
        return null
    }

    override fun onWindowStartingActionMode(
        callback: ActionMode.Callback?,
        type: Int
    ): ActionMode? {
        Log.d("MyDialog", "onWindowStartingActionMode")
        return null
    }

    override fun onActionModeStarted(mode: ActionMode?) {
        Log.d("MyDialog", "onActionModeStarted")
    }

    override fun onActionModeFinished(mode: ActionMode?) {
        Log.d("MyDialog", "onActionModeFinished")
    }
}
