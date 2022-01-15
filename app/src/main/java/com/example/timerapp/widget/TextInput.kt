package com.example.timerapp.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.example.timerapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

open class TextInput(context: Context, attrs: AttributeSet) :
    FrameLayout(
        context,
        attrs,
        com.google.android.material.R.attr.textInputStyle
    ) {

    var textInput: TextInputLayout =
        LayoutInflater.from(context).inflate(R.layout.widget_text_input, this, false) as TextInputLayout

    var editText: TextInputEditText = textInput.findViewById(R.id.editText)

    init {
        setWillNotDraw(false)

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.TextInput
        )

        val hint = a.getString(R.styleable.TextInput_android_hint)
        textInput.hint = hint

        editText.id = generateViewId()

        val inputType = a.getInt(R.styleable.TextInput_android_inputType, 0)
        if(inputType != 0)
            editText.inputType = inputType

        val minHeight = a.getDimensionPixelSize(R.styleable.TextInput_android_minHeight, resources.getDimensionPixelSize(R.dimen.selectable_min_height))
        minimumHeight = minHeight
        editText.minHeight = minHeight

        editText.gravity = Gravity.TOP
        addView(textInput)

        a.recycle()
    }

}