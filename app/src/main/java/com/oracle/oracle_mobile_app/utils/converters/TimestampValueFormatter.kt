package com.oracle.oracle_mobile_app.utils.converters

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*

class TimestampValueFormatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val cal = Calendar.getInstance()
        cal.time = Date(value.toLong() * 1000)
        return cal.get(Calendar.DAY_OF_MONTH).toString() + "-" + cal.get(Calendar.MONTH).toString() + "-" + cal.get(Calendar.YEAR).toString()
    }
}