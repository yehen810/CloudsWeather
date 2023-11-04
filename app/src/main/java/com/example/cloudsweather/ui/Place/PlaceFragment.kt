package com.example.cloudsweather.ui.Place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloudsweather.R
import com.example.cloudsweather.showToast
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adpter: PlaceAdpter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_place,container,false)
        return view
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adpter = PlaceAdpter(this,viewModel.placeList)
        recyclerView.adapter = adpter
        //实现搜索城市数据请求
        searchPlaceEdit.addTextChangedListener {
            edit -> val content = edit.toString()
            if(content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                adpter.notifyDataSetChanged()
            }
        }
        //
        viewModel.placeListData.observe(this, Observer {
            result -> val places = result.getOrNull()
            if (places != null){
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adpter.notifyDataSetChanged()
            }else{
                /*Toast.makeText(activity,"未能查询到任何地点",Toast.LENGTH_SHORT).show()*/
                "未能查询到任何地点".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }

        })

        
    }
}