package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivityDetailFavoriteBinding

class DetailFavoriteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailFavoriteBinding

    val title_vp = intArrayOf(
        R.string.moview,
        R.string.tv
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}