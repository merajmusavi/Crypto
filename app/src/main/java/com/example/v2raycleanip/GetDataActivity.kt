package com.example.v2raycleanip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
                    val btc = jsonArray.getJSONObject(0)
                    val name = btc.getString("name")

                    runOnUiThread {
                        binding.textView4.text = name
                    }

                }

            })
        }
    }
