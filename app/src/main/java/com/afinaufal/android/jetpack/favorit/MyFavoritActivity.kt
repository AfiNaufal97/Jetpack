package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivityMyFavoritBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyFavoritActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMyFavoritBinding

    companion object{
        val TITLE = intArrayOf(
            R.string.moview,
            R.string.tv
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVp()
    }

    private fun setVp() {
        val viewPaggerFavoritAdapter = AdapterFavViewPagger(this)
        val viewPaggerFav = binding.vpFavoritActivity
        viewPaggerFav.adapter = viewPaggerFavoritAdapter
        val tabLy = binding.tabLayoutFavoritActivity
        TabLayoutMediator(tabLy, viewPaggerFav){tab_layout, position ->
            tab_layout.text = getString(TITLE[position])
        }.attach()
    }
}