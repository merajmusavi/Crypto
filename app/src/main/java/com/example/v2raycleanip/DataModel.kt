package com.example.v2raycleanip

data class DataModel(var key:String,var name_en:String,var daily_high_price:Double,var daily_low_price:Double,var percent_change_1h:String){
    override fun toString(): String {
        return "DataModel(key='$key', name_en='$name_en', daily_high_price='$daily_high_price', daily_low_price='$daily_low_price', percent_change_1h='$percent_change_1h')"
    }
}
