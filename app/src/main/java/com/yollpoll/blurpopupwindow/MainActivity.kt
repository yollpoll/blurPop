package com.yollpoll.blurpopupwindow

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rl_bg.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                setblur(rl_bg, tv_hello, RADIUS, SCALE_FACTOR)
                rl_bg.viewTreeObserver
                    .removeOnPreDrawListener(this)
                return true
            }
        })

    }


    public fun show(view: View) {
        val pop = BlurPop(this, R.layout.popup_common, rl_bg, 400, 400);
        pop.show(view)
    }
}