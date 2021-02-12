package com.yollpoll.blurpopupwindow

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by spq on 2021/1/14
 */

class BlurPop constructor(
    _mContext: Context,
    _layoutId: Int,
    _bgView: View,
    _mWidth: Int = WindowManager.LayoutParams.WRAP_CONTENT,
    _mHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT
) {
    private val mPopupWindow: PopupWindow = PopupWindow()
    val bgView: View = _bgView
    val mHeight: Int = _mHeight
    val mWidth: Int = _mWidth
    val mContext = _mContext
    val layoutId: Int = _layoutId

    init {
        initWindow()
    }
    fun show(anchor: View) {
        mPopupWindow.showAsDropDown(anchor)
    }

    /**
     * 初始化窗口
     */
    fun initWindow() {
        mPopupWindow.isOutsideTouchable = false;
        mPopupWindow.width = mWidth
        mPopupWindow.height = mHeight
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(mContext),
            layoutId,
            null,
            false
        )
        mPopupWindow.contentView = binding.root
        mPopupWindow.contentView.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                setblur(bgView, mPopupWindow.contentView, RADIUS, SCALE_FACTOR)
                mPopupWindow.contentView.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
        mPopupWindow.isFocusable = true
//        mPopupWindow.setTouchInterceptor(OnTouchListener { v, event ->
//            if (event.action == MotionEvent.ACTION_OUTSIDE) {
//                mPopupWindow.dismiss()
//                return@OnTouchListener true
//            }
//            false
//        })
        mPopupWindow.setBackgroundDrawable(BitmapDrawable())
        mPopupWindow.dismiss()

    }

    interface OnDisListener {
        fun onDismiss()
    }
}