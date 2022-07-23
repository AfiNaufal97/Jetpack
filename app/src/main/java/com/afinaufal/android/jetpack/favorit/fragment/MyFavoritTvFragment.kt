package com.afinaufal.androiddasar.afinaufalsubmission_1.favorit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afinaufal.androiddasar.afinaufalsubmission_1.databinding.FragmentMyFavoritTvBinding
import com.afinaufal.androiddasar.afinaufalsubmission_1.myviewmodel.MyViewModelFactory
import com.afinaufal.androiddasar.afinaufalsubmission_1.tv.AdapterTv
import com.afinaufal.androiddasar.afinaufalsubmission_1.tv.TvViewModel

class MyFavoritTvFragment : Fragment() {

    private var _binding:FragmentMyFavoritTvBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyFavoritTvBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = MyViewModelFactory.getInstance(requireActivity())
        val viewModelTv = ViewModelProvider(this, factory)[MyTvFavoritViewModel::class.java]

        val adapterTv = AdapterTv()
        viewModelTv.getFavorit().observe(viewLifecycleOwner, {myTv ->
            adapterTv.setTvFragment(myTv)
            adapterTv.submitList(myTv)
            adapterTv.notifyDataSetChanged()
        })

        binding!!.rvFavoritTv.apply {
            setHasFixedSize(true)
            adapter = adapterTv
            layoutManager = LinearLayoutManager(activity)
        }
    }

}