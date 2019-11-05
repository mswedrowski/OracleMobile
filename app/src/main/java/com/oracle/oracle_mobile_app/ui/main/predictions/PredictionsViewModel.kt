package com.oracle.oracle_mobile_app.ui.main.predictions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders

class PredictionsViewModel : ViewModel() {

    var predictionsOrdersList  = MutableLiveData<List<AmountOfOrders>>().apply {
        value = listOf(
            AmountOfOrders(1,1514764800F,311F),
            AmountOfOrders(1,1514851200F,303F),
            AmountOfOrders(1,1514937600F,315F),
            AmountOfOrders(1,1515024000F,315F))
    }

}