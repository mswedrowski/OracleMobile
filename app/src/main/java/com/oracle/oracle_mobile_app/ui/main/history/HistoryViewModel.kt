package com.oracle.oracle_mobile_app.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.HistoryRecord

class HistoryViewModel : ViewModel() {

    var historyRecordList = MutableLiveData<List<HistoryRecord>>()

    init {
        historyRecordList.value = listOf()

        historyRecordList.postValue(listOf( HistoryRecord("Date1","Val1"),
                                            HistoryRecord("Date2","Val2"),
                                            HistoryRecord("Date3","Val3")))
    }
    private val _num_orders = MutableLiveData<String>().apply {
        value = "999999"
    }
    val text: LiveData<String> = _num_orders
}