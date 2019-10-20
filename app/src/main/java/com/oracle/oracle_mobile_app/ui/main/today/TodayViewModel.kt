package com.oracle.oracle_mobile_app.ui.main.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oracle.oracle_mobile_app.data.model.TodayOrderDistribution

class TodayViewModel : ViewModel() {

    var todayDistributionList = MutableLiveData<List<TodayOrderDistribution>>()

    var todayToYesterday = MutableLiveData<String>()
    var todayToYesterday_bool = MutableLiveData<Boolean>()


    init {
        todayDistributionList.value = listOf(   TodayOrderDistribution("Item1",20F),
                                                TodayOrderDistribution("Item2",50F),
                                                TodayOrderDistribution("Item3",10F),
                                                TodayOrderDistribution("Item4",20F))

        todayToYesterday.value = 0.15F.toString() + " %"
        todayToYesterday_bool.value = true
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
}