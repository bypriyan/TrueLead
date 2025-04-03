package com.bypriyan.sharemarketcourseinhindi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.sharemarketcourseinhindi.model.ModelOnBordingScreen
import com.socialseller.ceo.databinding.RowOnbordingPageBinding
import com.socialseller.clothcrew.utility.GlideHelper

class AdapterOnBoardingScreen(
    private val context: Context,
    private val elementList: List<ModelOnBordingScreen>
) : RecyclerView.Adapter<AdapterOnBoardingScreen.HolderOnBoardingScreen>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderOnBoardingScreen {
        val binding = RowOnbordingPageBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderOnBoardingScreen(binding)
    }

    override fun onBindViewHolder(holder: HolderOnBoardingScreen, position: Int) {
        val model = elementList[position]
        with(holder.binding) {
            title.text = model.title
            description.text = model.description
            GlideHelper.loadImage(imgOnBording, model.imgUrl)
        }
    }

    override fun getItemCount(): Int = elementList.size

    inner class HolderOnBoardingScreen(val binding: RowOnbordingPageBinding) : RecyclerView.ViewHolder(binding.root)
}