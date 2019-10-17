package com.oracle.oracle_mobile_app.ui.main.history

import androidx.lifecycle.MutableLiveData
import com.oracle.oracle_mobile_app.base.BaseViewModel
import com.oracle.oracle_mobile_app.data.model.HistoryRecord

class HistoryRecordViewModel : BaseViewModel() {
    private  var historyRecordDate = MutableLiveData<String>()
    private  var historyRecordValue = MutableLiveData<String>()

    fun bind(historyRec: HistoryRecord)
    {
        historyRecordDate.value = historyRec.date
        historyRecordValue.value = historyRec.value
    }

    fun getHistoryRecordValue() : MutableLiveData<String>{
        return historyRecordValue
    }

    fun getHistoryRecordDate() : MutableLiveData<String>{
        return historyRecordDate
    }
}