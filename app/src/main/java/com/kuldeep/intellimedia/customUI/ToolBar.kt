@file:Suppress("DEPRECATION")

package com.kuldeep.intellimedia.customUI

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.kuldeep.intellimedia.R
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 25-08-2021.
 * PS: Not easy to write code, please indicate.
 */
class ToolBar : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar, this, true)
    }

    fun setBackground(@DrawableRes resId: Int?) {
        toolbar.setBackgroundResource(resId!!)
    }

    fun setTitle(title: String) {
        txt_toolbar_title.text = title
    }

    fun setTitle(@StringRes resId: Int) {
        txt_toolbar_title.setText(resId)
    }


    fun setTitleColor(@ColorRes resId: Int) {
        txt_toolbar_title.setTextColor(resources.getColor(resId))
    }
}