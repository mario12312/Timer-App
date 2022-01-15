package com.example.timerapp.widget

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.example.timerapp.R
import com.google.android.material.button.MaterialButton
import org.jetbrains.anko.dip

@RequiresApi(Build.VERSION_CODES.M)
//Here I was creating a customized button, but I didn't use it
class BaseButton(context: Context, attrs: AttributeSet) :
    MaterialButton(
        context,
        attrs,
        com.google.android.material.R.attr.materialButtonStyle
    ) {

    enum class ButtonType {
        FILLED,
        OUTLINE;
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.BaseButton
        )

        val value = a.getInt(R.styleable.BaseButton_buttonType, ButtonType.FILLED.ordinal)
        if (value == ButtonType.OUTLINE.ordinal) {
            setTextColor(context.getColor(R.color.colorPrimary))
            backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.white))
            strokeColor = ColorStateList.valueOf(context.getColor(R.color.colorPrimary))
            strokeWidth = context.dip(1)
        }

        a.recycle()

        minWidth = resources.getDimensionPixelSize(R.dimen.button_width)
        minHeight = resources.getDimensionPixelSize(R.dimen.button_height)
    }
}