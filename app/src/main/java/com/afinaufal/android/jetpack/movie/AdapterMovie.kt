package com.afinaufal.androiddasar.afinaufalsubmission_1.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyMovieEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ListMovieBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.detail.DetailMovieActivity
import com.bumptech.glide.Glide

class AdapterMovie:PagedListAdapter<MyMovieEntity, AdapterMovie.MoviewViewHolder>(CALLBACK){

    companion object{
        private val CALLBACK = object :DiffUtil.ItemCallback<MyMovieEntity>(){
            override fun areItemsTheSame(oldItem: MyMovieEntity, newItem: MyMovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MyMovieEntity,
                newItem: MyMovieEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var listMovie = ArrayList<MyMovieEntity>()

    fun setValueMovie(movie:List<MyMovieEntity>){
        if(movie == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    class MoviewViewHolder(val binding:ListMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:MyMovieEntity) {
            with(binding.root){
                Glide.with(this)
                        .load(movie.imaage)
                        .into(binding.ivList)
                binding.tvTitle.text = movie.nameMovie
                binding.tvRatingValue.text = movie.raiting

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.VALUE, movie.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
       return MoviewViewHolder(ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie != null){
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return  listMovie.size
    }


}