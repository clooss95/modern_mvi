package com.example.modernmvi.feature.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modernmvi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
    }
}