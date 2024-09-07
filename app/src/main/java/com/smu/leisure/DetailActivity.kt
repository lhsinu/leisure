package com.smu.leisure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.leisure.base.CommonUtils
import com.smu.leisure.base.Constants
import com.smu.leisure.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var commonUtils = CommonUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Constants.LeisureData.s01.dYaw
        binding.tvS01.text = Constants.LeisureData.s01.dYaw.toString()
        if(Constants.LeisureData.s01.dYaw < 0) {
            binding.tvS01.setTextColor(getColor(R.color.red))
        }

        binding.tvS02.text = Constants.LeisureData.s02.dYaw.toString()
        if(Constants.LeisureData.s02.dYaw < 0) {
            binding.tvS02.setTextColor(getColor(R.color.red))
        }

        binding.tvS03.text = Constants.LeisureData.s03.dYaw.toString()
        if(Constants.LeisureData.s03.dYaw < 0) {
            binding.tvS03.setTextColor(getColor(R.color.red))
        }

        binding.tvS04.text = Constants.LeisureData.s04.dYaw.toString()
        if(Constants.LeisureData.s04.dYaw < 0) {
            binding.tvS04.setTextColor(getColor(R.color.red))
        }

        binding.tvS05.text = Constants.LeisureData.s05.dYaw.toString()
        if(Constants.LeisureData.s05.dYaw < 0) {
            binding.tvS05.setTextColor(getColor(R.color.red))
        }

        binding.tvS06.text = Constants.LeisureData.s06.dYaw.toString()
        if(Constants.LeisureData.s06.dYaw < 0) {
            binding.tvS06.setTextColor(getColor(R.color.red))
        }

        binding.tvS07.text = Constants.LeisureData.s07.dYaw.toString()
        if(Constants.LeisureData.s07.dYaw < 0) {
            binding.tvS07.setTextColor(getColor(R.color.red))
        }

        binding.tvS08.text = Constants.LeisureData.s08.dYaw.toString()
        if(Constants.LeisureData.s08.dYaw < 0) {
            binding.tvS08.setTextColor(getColor(R.color.red))
        }

        binding.tvS09.text = Constants.LeisureData.s09.dYaw.toString()
        if(Constants.LeisureData.s09.dYaw < 0) {
            binding.tvS09.setTextColor(getColor(R.color.red))
        }

        binding.tvS10.text = Constants.LeisureData.s10.dYaw.toString()
        if(Constants.LeisureData.s10.dYaw < 0) {
            binding.tvS10.setTextColor(getColor(R.color.red))
        }

        binding.tvS11.text = Constants.LeisureData.s11.dYaw.toString()
        if(Constants.LeisureData.s11.dYaw < 0) {
            binding.tvS11.setTextColor(getColor(R.color.red))
        }

        binding.tvS12.text = Constants.LeisureData.s12.dYaw.toString()
        if(Constants.LeisureData.s12.dYaw < 0) {
            binding.tvS12.setTextColor(getColor(R.color.red))
        }

        binding.tvS13.text = Constants.LeisureData.s13.dYaw.toString()
        if(Constants.LeisureData.s13.dYaw < 0) {
            binding.tvS13.setTextColor(getColor(R.color.red))
        }

        binding.tvS14.text = Constants.LeisureData.s14.dYaw.toString()
        if(Constants.LeisureData.s14.dYaw < 0) {
            binding.tvS14.setTextColor(getColor(R.color.red))
        }


    }
}

