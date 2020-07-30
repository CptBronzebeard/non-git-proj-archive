package com.ilin.simplecurrencyconverter

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class CustomViewPager : ViewPager {
    private var isPagingEnabled: Boolean

    constructor(context: Context?) : super(context!!) {
        isPagingEnabled = true
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        isPagingEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return isPagingEnabled && super.onTouchEvent(event)
    }

    //for samsung phones to prevent tab switching keys to show on keyboard
    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return isPagingEnabled && event.let { super.executeKeyEvent(it) }
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return isPagingEnabled && super.onInterceptTouchEvent(event)
    }

}