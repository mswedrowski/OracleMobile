package com.oracle.oracle_mobile_app.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import com.oracle.oracle_mobile_app.data.model.HistoryPurchase

class HistoryViewModel : ViewModel() {

    var historyRecordList = MutableLiveData<List<HistoryPurchase>>()
    var historyOrderNumList = MutableLiveData<List<AmountOfOrders>>()


    init {
        historyRecordList.value = listOf()

        historyRecordList.postValue(listOf( HistoryPurchase("Name1","Date1","Val1"),
                                            HistoryPurchase("Name2","Date2","Val2"),
                                            HistoryPurchase("Name3","Date3","Val3")))

        historyOrderNumList.value = (listOf(
                                            AmountOfOrders(1451660400,223F),
                                            AmountOfOrders(1451833400,240F),
                                            AmountOfOrders(1451919800,250F),
                                            AmountOfOrders(1452006200,222F),
                                            AmountOfOrders(1452092600,270F)
        ))
    }
    private val _num_orders = MutableLiveData<String>().apply {
        value = "999999"
    }
    val text: LiveData<String> = _num_orders
}