package com.example.v2raycleanip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import okhttp3.*
import org.apache.commons.math3.stat.regression.SimpleRegression
import org.json.JSONObject
import java.io.IOException


class PredictActiviy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predict_activiy)
        val historicalData = mutableListOf<Double>()
        val nextCost = 175.0
        var nameList = mutableListOf<DataModel>()
        val client1 = OkHttpClient()
        val request1 = Request.Builder().url("https://api.wallex.ir/v1/currencies/stats").build()
        client1.newCall(request1).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val x  = response.body!!.string()
                val jsonObject = JSONObject(x)
                val jsonArray = jsonObject.getJSONArray("result")
                    val option = jsonArray.getJSONObject(0)
                    val name = option.getString("name_en")
                    val daily_high_price = option.getDouble("daily_high_price")
                    val daily_low_price = option.getDouble("daily_low_price")

                    runOnUiThread {




                    }








            }


        })

        val client = OkHttpClient()
        var numResponsesReceived = 0
        for (i in 0..4) {
            val request = Request.Builder().url("https://api.kucoin.com/api/v1/market/stats?symbol=BTC-USDT").build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    val x = response.body!!.string()
                    val jsonObject = JSONObject(x)
                    val obj = jsonObject.getJSONObject("data")
                    historicalData.add(obj.getString("last").toDouble())

                    // Check if all responses have been received
                    numResponsesReceived++
                    if (numResponsesReceived == 5) {
                        // Predict the cost
                        val regression = SimpleRegression()
                        for (i in historicalData.indices) {
                            regression.addData(i.toDouble(), historicalData[i])
                        }
                        val predictedCost = regression.predict(historicalData.size.toDouble())
                        runOnUiThread {
                            Toast.makeText(this@PredictActiviy, predictedCost.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}
