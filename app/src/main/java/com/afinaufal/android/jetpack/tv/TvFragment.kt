package com.afinaufal.androiddasar.afinaufalsubmission_1.tv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.TvFragmentBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.dataenum.Status
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory

class TvFragment:Fragment(){
    private var _binding:TvFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = TvFragmentBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = MyViewModelFactory.getInstance(requireActivity())
            val viewModelTv = ViewModelProvider(this, factory)[TvViewModel::class.java]

            setRecylerview(viewModelTv)
        }
    }



    private fun setRecylerview(model:TvViewModel){
        val tvAdapterTv = AdapterTv()
        model.getDataTv().observe(viewLifecycleOwner, {myTv ->
            if(myTv != null){
                when(myTv.status){
                    Status.SUCCESS -> {
                        val tvData = myTv.data
                        if(tvData != null){
                            tvAdapterTv.setTvFragment(tvData)
                        }
                        tvAdapterTv.submitList(myTv.data)
                        tvAdapterTv.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        val rTv = binding!!.rvTv
        rTv.layoutManager = LinearLayoutManager(context)
        rTv.setHasFixedSize(true)
        rTv.adapter = tvAdapterTv
    }

}