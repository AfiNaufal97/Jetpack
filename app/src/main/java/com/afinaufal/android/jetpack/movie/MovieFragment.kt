package com.afinaufal.androiddasar.afinaufalsubmission_1.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.MovieFragmentBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.Status
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory

class MovieFragment:Fragment(){
    private var _binding:MovieFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val factory = MyViewModelFactory.getInstance(requireActivity())
            val viewModelMovie = ViewModelProvider(this, factory)[ViewModelMovie::class.java]

            setRecylerview(viewModelMovie)
    }


    private fun setRecylerview(model:ViewModelMovie){
        val movieAdapter = AdapterMovie()
        model.getMovie().observe(requireActivity(),{movie ->
            if(movie != null){
                when(movie.status){
                    Status.SUCCESS -> {
                        val movieData = movie.data
                        if (movieData != null) {
                            movieAdapter.setValueMovie(movieData)
                        }
                        movieAdapter.submitList(movie.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding!!.rvMovie){
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}