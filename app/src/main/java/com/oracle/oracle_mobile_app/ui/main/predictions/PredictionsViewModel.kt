package com.oracle.oracle_mobile_app.ui.main.predictions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders

class PredictionsViewModel : ViewModel() {

    var predictionsOrdersList  = MutableLiveData<List<AmountOfOrders>>().apply {
        value = listOf(
            AmountOfOrders(1514764800,311F),
            AmountOfOrders(1514851200,303F),
            AmountOfOrders(1514937600,315F),
            AmountOfOrders(1515024000,315F))
    }

}