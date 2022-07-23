package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.mysource.Resource
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivityDetailMovieBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.Status
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityDetailMovieBinding
    private lateinit var input:MyMovieEntity
    private var clickFavorit:ToggleButton? = null
    private lateinit var viewModel:ViewModelDetailMovie
    private var stateFavorit:Boolean = false


    companion object{
        val VALUE = "value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MyViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ViewModelDetailMovie::class.java]
        val extra = intent.extras
        if(extra != null){
        val id = extra.getInt(VALUE, 0)
            viewModel.setSelectedMovie(id)
        binding.progressBarMovie.visibility=View.VISIBLE
            viewModel.getMovie.observe(this, {movie ->
                if(movie != null){
                    when(movie.status){
                        Status.SUCCESS -> if(movie.data != null){
                            input = movie.data
                            setView(input)
                            if(movie.data.favorite == true){
                                clickFavorit = binding.ivFavorite
                                clickFavorit!!.isChecked = true
                            }else{
                                clickFavorit = binding.ivFavorite
                                clickFavorit!!.isChecked = false
                            }
                        }
                        Status.ERROR ->{
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        binding.toolbarDetail.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    private fun setView(input:MyMovieEntity) {
        binding.progressBarMovie.visibility=View.GONE
        Glide.with(this)
            .load(input.imaage)
            .into(binding.ivDetailMovie)
        binding.tvDetailTitleMovie.text = input.nameMovie
        binding.tvRaitingValueMovie.text = input.raiting
        binding.tvYearValue.text = input.year
        binding.tvGenreMovieValue.text = input.genre
        binding.keteranganMovie.text = input.sinopsis
        binding.tvDurationValue.text = input.duration
        binding.btnShare.setOnClickListener(this)
        binding.btnTrailer.setOnClickListener(this)
        binding.ivFavorite.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_trailer -> {
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, input.trailer)
                    startActivity(Intent.createChooser(this, "Pilih Media"))
                }
            }
            R.id.btn_share ->{
                Intent(Intent.ACTION_VIEW).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, input.link)
                    startActivity(Intent.createChooser(this, "Pilih Media"))
                }
            }
            R.id.iv_favorite ->{
                viewModel.getMovie.observe(this, {movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.SUCCESS -> if (movie.data != null) {
                                    if(movie.data.favorite == false){
                                        movie.data.favorite = true
                                        setFavorit(movie.data.favorite)
                                    }else{
                                        movie.data.favorite = false
                                        setFavorit(movie.data.favorite)
                                    }
                                }
                                Status.ERROR -> {
                                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                })
                viewModel.setFavoritMovie()
            }
        }
    }

    private fun setFavorit(stateFavoritUser: Boolean) {
        if(clickFavorit == null)return
        val click = binding.ivFavorite
        if(!stateFavoritUser){
            click.isChecked = true
        }else{
            click.isChecked = false
        }
    }
}