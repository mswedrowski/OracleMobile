package com.oracle.oracle_mobile_app.ui.main.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.data.PieEntry
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.databinding.FragmentTodayBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import android.graphics.Color


class TodayFragment : Fragment() {

    private lateinit var todayViewModel: TodayViewModel
    private lateinit var binding: FragmentTodayBinding
    var chart_entries = mutableListOf<PieEntry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todayViewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)


        todayViewModel.todayDistributionList.value?.forEach{ distr ->
            chart_entries.add(PieEntry(distr.distribution,distr.itemName))
        }
        val root = inflater.inflate(R.layout.fragment_today, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pieColors = intArrayOf(
            Color.rgb(192, 0, 0),
            Color.rgb(255, 0, 0),
            Color.rgb(255, 192, 0),
            Color.rgb(127, 127, 127),
            Color.rgb(146, 208, 80),
            Color.rgb(0, 176, 80),
            Color.rgb(79, 129, 189)
        )

        binding = FragmentTodayBinding.bind(view).apply {


            val colorListInt = mutableListOf<Int>()

            pieColors.forEach { color ->
                colorListInt.add(color)
            }

            val dataSet = PieDataSet(chart_entries, "Orders distribution")
            dataSet.colors = colorListInt
            todayDistributionChart.data = PieData(dataSet)
            todayDistributionChart.description.isEnabled = false

            todayDistributionChart.invalidate()

            viewModel = todayViewModel

        }
    }
}