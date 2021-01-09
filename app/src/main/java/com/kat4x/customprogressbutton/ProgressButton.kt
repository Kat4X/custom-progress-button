package com.kat4x.customprogressbutton

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.kat4x.customprogressbutton.databinding.ButtonCustomProgressBinding

class ProgressButton constructor(
    private val context: Context,
    view: View,
    private val buttonText: String? = null
) {

    private val labelText: TextView
    private val rootLayout: MaterialCardView
    private val progressInd: CircularProgressIndicator
    private var stateFinished: Boolean = false


    private val fadeInAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
    private val binding: ButtonCustomProgressBinding = ButtonCustomProgressBinding.bind(view)

    init {
        labelText = binding.textLabel
        rootLayout = binding.rootLayout
        progressInd = binding.progressCircle
        rootLayout.setCardBackgroundColor(ContextCompat.getColor(context, R.color.crimson))
        labelText.text = buttonText ?: context.getString(R.string.app_name)
    }

    fun buttonReset() {
        progressInd.visibility = View.INVISIBLE
        rootLayout.setCardBackgroundColor(ContextCompat.getColor(context, R.color.crimson))
        labelText.text = buttonText ?: context.getString(R.string.app_name)
        labelText.animation = fadeInAnim
        stateFinished = false
    }

    fun buttonActivated() {
        progressInd.visibility = View.VISIBLE
        progressInd.animation = fadeInAnim
        rootLayout.setCardBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
        labelText.animation = fadeInAnim
        labelText.text = context.getString(R.string.please_wait)
        binding.root.isClickable = false
        stateFinished = false
    }

    fun buttonFinished() {
        rootLayout.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green_400))
        progressInd.visibility = View.INVISIBLE
        labelText.text = context.getString(R.string.done)
        labelText.animation = fadeInAnim
        binding.root.isClickable = true
        stateFinished = true
    }

    fun getButtonState(): Boolean {
        return stateFinished
    }

}