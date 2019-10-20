package com.oracle.oracle_mobile_app.ui.main.predictions

import androidx.lifecycle.MutableLiveData
import com.oracle.oracle_mobile_app.base.BaseViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import java.util.*

class PredictionsRecordViewModel : BaseViewModel() {

    private  var predictionsDate = MutableLiveData<String>()
    private  var predictionsValue = MutableLiveData<String>()

    fun bind(orders: AmountOfOrders)
    {
        predictionsDate.value = timeStampToDate(orders.dateTimeStamp.toFloat())
        predictionsValue.value = orders.value.toString()
    }

    fun getPredictionsRecordValue() : MutableLiveData<String>{
        return predictionsValue
    }

    fun getPredictionsRecordDate() : MutableLiveData<String>{
        return predictionsDate
    }

    private fun timeStampToDate(value:Float):String {
        val cal = Calendar.getInstance()
        cal.time = Date(value.toLong() * 1000)
        return cal.get(Calendar.DAY_OF_MONTH).toString() + "-" + cal.get(Calendar.MONTH).toString() + "-" + cal.get(
            Calendar.YEAR).toString()
    }
}