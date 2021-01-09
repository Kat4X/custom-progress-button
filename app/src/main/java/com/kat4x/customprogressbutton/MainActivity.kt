package com.kat4x.customprogressbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kat4x.customprogressbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progressButton: ProgressButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressButton = ProgressButton(this, binding.button.root, getString(R.string.click_me))

        binding.button.root.setOnClickListener {
            if (progressButton.getButtonState()) {
                progressButton.buttonReset()
            } else {
                progressButton.buttonActivated()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    progressButton.buttonFinished()
                }, 3000)
            }
        }
    }
}