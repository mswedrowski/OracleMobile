package com.oracle.oracle_mobile_app.ui.main.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_history.*
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.oracle.oracle_mobile_app.utils.converters.TimestampValueFormatter
import android.preference.PreferenceManager






class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding
    var chart_entries = mutableListOf<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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

    override fun onCreateOptionsMenu(menu: Menu,menuInflater: MenuInflater) {
        val inflater: MenuInflater = menuInflater
        val savedIndex = PreferenceManager.getDefaultSharedPreferences(activity).getInt("NAME", 1)
        for (i in 1..3) {
            val item = menu.findItem(getMenuIdByIndex(i))
            if (item != null) {
                item.isChecked = i == savedIndex
            }
        }

        inflater.inflate(R.menu.history_action_bar_menu, menu)

        menu.findItem(R.id.full_history).isChecked = true
    }

    private fun getMenuIdByIndex(index: Int): Int {
        when (index) {
            1 -> return R.id.last_month
            2 -> return R.id.last_year
            3 -> return R.id.full_history
        }
        return 0
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        item.isChecked = !item.isChecked

        return when(item.itemId){
            R.id.last_month -> {    PreferenceManager.getDefaultSharedPreferences(activity).edit().putInt("NAME", 1).apply()
                true
            }

            R.id.last_year -> {     PreferenceManager.getDefaultSharedPreferences(activity).edit().putInt("NAME", 2).apply()
                true
            }

            R.id.full_history -> {  PreferenceManager.getDefaultSharedPreferences(activity).edit().putInt("NAME", 3).apply()
                true
            }

            else ->
                super.onOptionsItemSelected(item)
        }
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