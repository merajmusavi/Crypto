package com.example.v2raycleanip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.v2raycleanip.databinding.ActivityGetDataBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class GetDataActivity : AppCompatActivity() {
    lateinit var binding:ActivityGetDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = arrayOf("meraj","ahmad")
        var nameList = mutableListOf<DataModel>()

        binding.rec.layoutManager = LinearLayoutManager(this)
            val client = OkHttpClient()
            val request = Request.Builder().url("https://api.wallex.ir/v1/currencies/stats").build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    val x  = response.body!!.string()
                    val jsonObject = JSONObject(x)
                    val jsonArray = jsonObject.getJSONArray("result")

             for(i in 0..13){
                 val option = jsonArray.getJSONObject(i)
                 val name = option.getString("name_en")
                 val daily_high_price = option.getDouble("daily_high_price")
                 val daily_low_price = option.getDouble("daily_low_price")

                 runOnUiThread {

                     nameList.add(DataModel("",name,daily_high_price,daily_low_price,""))
                     binding.rec.adapter = MyAdapter(this@GetDataActivity,nameList)


                 }
             }







                }


            })


    }
    }
