package com.oracle.oracle_mobile_app.ui.main.history

import androidx.lifecycle.MutableLiveData
import com.oracle.oracle_mobile_app.base.BaseViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import com.oracle.oracle_mobile_app.data.model.HistoryPurchase
import java.util.*

class HistoryRecordViewModel : BaseViewModel() {
    private  var historyRecordDate = MutableLiveData<String>()
    private  var historyRecordValue = MutableLiveData<String>()
    private  var historyRecordName = MutableLiveData<String>()

    fun bind(historyRec: AmountOfOrders)
    {
        historyRecordDate.value = timeStampToDate(historyRec.date.toFloat())
        historyRecordValue.value = historyRec.value.toString()
       // historyRecordName.value = historyRec.itemName
    }

    fun getHistoryRecordValue() : MutableLiveData<String>{
        return historyRecordValue
    }

    fun getHistoryRecordDate() : MutableLiveData<String>{
        return historyRecordDate
    }

    fun getHistoryRecordName() : MutableLiveData<String>{
        return historyRecordName
    }

    private fun timeStampToDate(value:Float):String {
        val cal = Calendar.getInstance()
        cal.time = Date(value.toLong() * 1000)
        return cal.get(Calendar.DAY_OF_MONTH).toString() + "-" + cal.get(Calendar.MONTH).toString() + "-" + cal.get(
            Calendar.YEAR).toString()
    }
}