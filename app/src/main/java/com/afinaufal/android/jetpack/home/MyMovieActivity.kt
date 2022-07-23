package com.afinaufal.androiddasar.afinaufalsubmission_1.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivityMymovieBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.DetailFavoriteActivity
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.MyFavoritActivity
import com.google.android.material.tabs.TabLayoutMediator

class MyMovieActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding:ActivityMymovieBinding

    private val NAME_TAB = intArrayOf(
        R.string.moview,R.string.tv
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMymovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarHome.setOnMenuItemClickListener(this)
        setViewPagger()
    }

    private fun setViewPagger(){
        val viewPgger = binding.vpHome
        val tabLayout = binding.tabLayout

        val adapterViewPagger = AdapterViewPagger(this)
        viewPgger.adapter = adapterViewPagger
        TabLayoutMediator(tabLayout, viewPgger){
            tab,posisi -> tab.text = getString(NAME_TAB[posisi])
        }.attach()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.btn_profile -> {
                val view = AlertDialog.Builder(this)
                view.setView(R.layout.my_profile_layout)
                view.setNegativeButton("close"){
                    dialog,_ -> dialog.dismiss()
                }
                view.show()
            }
            R.id.btn_to_favorit -> startActivity(Intent(this, MyFavoritActivity::class.java))
        }
        return true
    }
}