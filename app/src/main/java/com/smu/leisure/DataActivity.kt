package com.smu.leisure

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smu.leisure.base.Constants
import com.smu.leisure.base.MyApplication
import com.smu.leisure.databinding.ActivityDataBinding
import com.smu.leisure.db.AppDatabase
import com.smu.leisure.db.LeisureDao
import com.smu.leisure.db.LeisureEntity

class DataActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityDataBinding

    lateinit var db : AppDatabase
    lateinit var leisureDao: LeisureDao
    private lateinit var leisureList: ArrayList<LeisureEntity>
    private lateinit var adapter : DataRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        leisureDao = db.getLeisureDao()

        getAllDataList()
    }

    override fun onRestart() {
        super.onRestart()
        getAllDataList()
    }

    private fun getAllDataList() {
        Thread {
            leisureList = ArrayList(leisureDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            adapter = DataRecyclerViewAdapter(this, leisureList, this)
            binding.rvLeisureData.adapter = adapter
            binding.rvLeisureData.layoutManager = LinearLayoutManager(MyApplication.ApplicationContext())
        }
    }

    override fun onDataItemClick(position: Int) {
        Constants.LeisureData = leisureList[position]
        val intent = Intent(this@DataActivity, DetailActivity::class.java)
        startActivity(intent)
    }

//    override fun onDataButtonClick(position: Int) {
//        val strCreatedDate = LeisureList[position].createddate
//        val bDown = LeisureList[position].download
//        val nId = LeisureList[position].id
//        val strType = LeisureList[position].type
//
//        Thread {
//            LeisureDao.updateDownloadTodo(nId!!, true)
//        }.start()
//
//        Constants.strSaveFileName = strCreatedDate
//
//        Constants.nStatusIndex = Constants.STATUS_INDEX_RAWDATA
//        sendMessage("send_$strType$strCreatedDate")
//    }

    private fun sendMessage(message: String) {
        Thread {
            ObNetworkClient.sendString(message)

            val response = ObNetworkClient.receiveString()
        }.start()
    }
}