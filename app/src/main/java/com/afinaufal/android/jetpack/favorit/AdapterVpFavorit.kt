package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyFavoritMovieFragment
import com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment.MyFavoritTvFragment

class AdapterVpFavorit(activity: AppCompatActivity):FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment?
        when(position){
            1 -> fragment = MyFavoritMovieFragment()
            else -> fragment = MyFavoritTvFragment()
        }

        return fragment
    }

}