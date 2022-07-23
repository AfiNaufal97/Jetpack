package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.FragmentMyFavoritMovieBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.movie.AdapterMovie
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory

class MyFavoritMovieFragment : Fragment() {

    private var _binding:FragmentMyFavoritMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding = FragmentMyFavoritMovieBinding.inflate(inflater, container, false)
        val view  = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = MyViewModelFactory.getInstance(requireActivity())
        val viewModelMovie = ViewModelProvider(this, factory)[MyMovieFavoritViewModel::class.java]

        var adapterFavMovie = AdapterMovie()
        viewModelMovie.getFavorit().observe(viewLifecycleOwner, {myMovie ->
            adapterFavMovie.setValueMovie(myMovie)
            adapterFavMovie.submitList(myMovie)
            adapterFavMovie.notifyDataSetChanged()
        })

        binding!!.rvFavoritMovie.apply {
            setHasFixedSize(true)
            adapter = adapterFavMovie
            layoutManager = LinearLayoutManager(activity)
        }
    }

}