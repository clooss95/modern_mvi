package com.bonacode.modernmvi.sample.presentation.feature.dogs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bonacode.modernmvi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)
    }
}
