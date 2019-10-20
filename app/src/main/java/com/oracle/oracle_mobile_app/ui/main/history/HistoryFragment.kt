package com.oracle.oracle_mobile_app.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_history.*
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData

import com.oracle.oracle_mobile_app.utils.converters.TimestampValueFormatter


class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding
    var chart_entries = mutableListOf<Entry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel = HistoryViewModel()


        historyViewModel.historyOrderNumList.value?.forEach { order_amnt ->
            chart_entries.add(Entry(order_amnt.dateTimeStamp.toFloat(),order_amnt.value))
        }


        val root = inflater.inflate(R.layout.fragment_history, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentHistoryBinding.bind(view).apply {

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = HistoryAdapter()



            val dataSet = LineDataSet(chart_entries, "Number of orders")

            val xAxisFormatter = TimestampValueFormatter()

            historyOrderNumChart.xAxis.valueFormatter = xAxisFormatter


            historyOrderNumChart.data = LineData(dataSet)
            historyOrderNumChart.description.isEnabled = false
            historyOrderNumChart.legend.isEnabled = false
            historyOrderNumChart.setPinchZoom(true)
            historyOrderNumChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            historyOrderNumChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            historyOrderNumChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            historyOrderNumChart.invalidate()

            viewModel = historyViewModel
            history_frag_rv.layoutManager = layoutManager
            history_frag_rv.adapter = adapter

        }
    }
}