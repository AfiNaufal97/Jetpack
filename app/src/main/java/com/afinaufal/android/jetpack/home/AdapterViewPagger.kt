package com.afinaufal.androiddasar.afinaufalsubmission_1.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.afinaufal.androiddasar.afinaufalsubmission_1.movie.MovieFragment
import com.afinaufal.androiddasar.afinaufalsubmission_1.tv.TvFragment

class AdapterViewPagger(activity:AppCompatActivity):FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MovieFragment()
            else -> fragment = TvFragment()
        }
        return fragment
    }


}