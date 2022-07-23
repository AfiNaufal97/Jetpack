package com.afinaufal.androiddasar.afinaufalsubmission_1.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afinaufal.androiddasar.afinaufalsubmission_1.data.lokal.MyTvEntity
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.ListTvBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.detail.DetailTvActivity
import com.bumptech.glide.Glide

class AdapterTv:PagedListAdapter<MyTvEntity, AdapterTv.TvViewHolder>(DIFF_CALLBACK_MY_TV) {

    private var listTv = ArrayList<MyTvEntity>()

    companion object{
        private val DIFF_CALLBACK_MY_TV = object :DiffUtil.ItemCallback<MyTvEntity>(){
            override fun areItemsTheSame(oldItem: MyTvEntity, newItem: MyTvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyTvEntity, newItem: MyTvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun setTvFragment(masukan:List<MyTvEntity>){
        if (masukan == null)return
        this.listTv.clear()
        this.listTv.addAll(masukan)
    }

    inner class TvViewHolder(val binding:ListTvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMasukan: MyTvEntity) {
            with(this){
                Glide.with(this.itemView)
                        .load(dataMasukan.images)
                        .into(binding.ivList)
                binding.tvTitle.text = dataMasukan.nameShow
                binding.raitingTvValueList.text = dataMasukan.raiting

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.VALUE, dataMasukan.id)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(ListTvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if(tv != null){
            holder.bind(tv)
        }
    }

    override fun getItemCount(): Int {
       return listTv.size
    }
}