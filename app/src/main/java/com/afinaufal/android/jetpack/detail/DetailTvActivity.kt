package com.afinaufal.androiddasar.afinaufalsubmission_1.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ActivityDetailTvBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.Status
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory
import com.bumptech.glide.Glide

class DetailTvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityDetailTvBinding
    private lateinit var factory:MyViewModelFactory
    private lateinit var viewModelTv:ViewModelDetailTv
    private lateinit var input:MyTvEntity
    private var clickChecked:ToggleButton? = null


    companion object{
        val VALUE = "value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarDetailTv.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.progressBarTv.visibility=View.VISIBLE
        factory = MyViewModelFactory.getInstance(this)
        viewModelTv = ViewModelProvider(this, factory)[ViewModelDetailTv::class.java]
        val extras = intent.extras
        if(extras != null){
            val id = extras.getInt(VALUE, 0)
            binding.progressBarTv.visibility = View.VISIBLE
            viewModelTv.setSelectTv(id)
            viewModelTv.getDataTv.observe(this, {myTv ->
                if(myTv != null){
                    when(myTv.status){
                        Status.SUCCESS -> if(myTv.data != null){
                            input = myTv.data
                            setView(input)
                            if(myTv.data.favorit == true){
                                clickChecked = binding.ivFoavoritTv
                                clickChecked!!.isChecked = true
                            }else{
                                clickChecked = binding.ivFoavoritTv
                                clickChecked!!.isChecked = false
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }


    private fun setView(input:MyTvEntity) {
        binding.progressBarTv.visibility=View.GONE
        binding.tvDetailTitleTv.text = input.nameShow
        binding.tvDurationTvValue.text = input.duration
        binding.tvGenreTvValue.text = input.genre
        binding.tvRaitingValueTv.text = input.raiting
        binding.tvSinopsisValueTv.text = input.sinopsis
        Glide.with(this)
                .load(input.images)
                .into(binding.ivDetailTv)

        binding.btnShareTv.setOnClickListener(this)
        binding.btnTrailerTv.setOnClickListener(this)
        binding.ivFoavoritTv.setOnClickListener(this)
    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.btn_share_tv-> {
               Intent(Intent.ACTION_SEND).apply {
                   type = "text/plain"
                   putExtra(Intent.EXTRA_TEXT, input.link)
                   startActivity(Intent.createChooser(this, "Pilih Media"))
               }
           }
           R.id.btn_trailer_tv -> {
               Intent(Intent.ACTION_SEND).apply {
                   type = "text/plain"
                   putExtra(Intent.EXTRA_TEXT, input.trailer)
                   startActivity(Intent.createChooser(this, "Pilih Media"))
               }
           }

           R.id.iv_foavorit_tv ->{
               viewModelTv.getDataTv.observe(this, {myTv ->
                   if(myTv != null){
                       when(myTv.status){
                           Status.SUCCESS -> if(myTv.data != null){
                               if(myTv.data.favorit == false){
                                    myTv.data.favorit = true
                                    setFavoritTv(myTv.data.favorit)
                               }else{
                                   myTv.data.favorit = false
                                   setFavoritTv(myTv.data.favorit)
                               }
                           }
                           Status.ERROR -> {
                               Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                           }
                       }
                   }
               })
               viewModelTv.setFavoritMovie()
           }
       }
    }

    private fun setFavoritTv(favorit: Boolean) {
        if(clickChecked == null)return
        val click = binding.ivFoavoritTv
        if(!favorit){
            click.isChecked = true
        }else{
            click.isChecked = false
        }
    }

}