package com.afinaufal.androiddasar.afinaufalsubmission_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivitySpalshScreenBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.home.MyMovieActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SpalshScreen : AppCompatActivity() {

    private lateinit var binding:ActivitySpalshScreenBinding
    val actvityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actvityScope.launch {
            delay(2500)
            startActivity(Intent(this@SpalshScreen, MyMovieActivity::class.java))
            finish()
        }

    }
}