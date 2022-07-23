package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyFavoritMovieFragment
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyFavoritTvFragment

class AdapterFavViewPagger(actvity:AppCompatActivity):FragmentStateAdapter(actvity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        when(position){
            0 -> fragment = MyFavoritMovieFragment()
            else -> fragment = MyFavoritTvFragment()
        }
        return fragment
    }

}