package com.oracle.oracle_mobile_app.ui.main.history

import androidx.lifecycle.MutableLiveData
import com.oracle.oracle_mobile_app.base.BaseViewModel
import com.oracle.oracle_mobile_app.data.model.HistoryPurchase

class HistoryRecordViewModel : BaseViewModel() {
    private  var historyRecordDate = MutableLiveData<String>()
    private  var historyRecordValue = MutableLiveData<String>()
    private  var historyRecordName = MutableLiveData<String>()

    fun bind(historyRec: HistoryPurchase)
    {
        historyRecordDate.value = historyRec.date
        historyRecordValue.value = historyRec.value
        historyRecordName.value = historyRec.name
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
}