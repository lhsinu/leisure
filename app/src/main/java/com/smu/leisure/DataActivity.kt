package com.smu.leisure

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smu.leisure.base.Constants
import com.smu.leisure.base.MyApplication
import com.smu.leisure.databinding.ActivityDataBinding
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.GolfDao
import com.smu.leisure.db.GolfEntity

class DataActivity : AppCompatActivity(), OnItemClickListener  {
    private lateinit var binding: ActivityDataBinding

    lateinit var db : AppDatabase
    lateinit var GolfDao: GolfDao
    private lateinit var GolfList: ArrayList<GolfEntity>
    private lateinit var adapter : DataRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        GolfDao = db.getGolfDao()

        getAllDataList()
    }

    override fun onRestart() {
        super.onRestart()
        getAllDataList()
    }

    private fun getAllDataList() {
        Thread {
            GolfList = ArrayList(GolfDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            adapter = DataRecyclerViewAdapter(GolfList, this, this)
            binding.rvSwingData.adapter = adapter
            binding.rvSwingData.layoutManager = LinearLayoutManager(MyApplication.ApplicationContext())
        }
    }

    override fun onDataItemClick(position: Int) {
        Constants.golfData = GolfList[position]
        val intent = Intent(this@DataActivity, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onDataButtonClick(position: Int) {
        val strCreatedDate = GolfList[position].createddate
        val bDown = GolfList[position].download
        val nId = GolfList[position].id
        val strType = GolfList[position].type

        Thread {
            GolfDao.updateDownloadTodo(nId!!, true)
        }.start()

        Constants.strSaveFileName = strCreatedDate

        Constants.nStatusIndex = Constants.STATUS_INDEX_RAWDATA
        sendMessage("send_$strType$strCreatedDate")
    }

    private fun sendMessage(message: String) {
        Thread {
            ObNetworkClient.sendString(message)

            val response = ObNetworkClient.receiveString()
        }.start()
    }
}