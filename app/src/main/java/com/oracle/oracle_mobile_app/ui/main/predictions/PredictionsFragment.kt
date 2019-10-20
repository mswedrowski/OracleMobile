package com.oracle.oracle_mobile_app.ui.main.predictions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.databinding.FragmentPredictionsBinding
import com.oracle.oracle_mobile_app.utils.converters.TimestampValueFormatter
import kotlinx.android.synthetic.main.fragment_predictions.*

class PredictionsFragment : Fragment() {

    private lateinit var predictionsViewModel: PredictionsViewModel
    private lateinit var binding: FragmentPredictionsBinding
    var chart_entries = mutableListOf<Entry>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        predictionsViewModel =
            ViewModelProviders.of(this).get(PredictionsViewModel::class.java)

        predictionsViewModel.predictionsOrdersList.value?.forEach { pred_amount ->
            chart_entries.add(Entry(pred_amount.dateTimeStamp.toFloat(),pred_amount.value)) }
        val root = inflater.inflate(R.layout.fragment_predictions, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PredictionsAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding = FragmentPredictionsBinding.bind(view).apply{

            val dataSet = LineDataSet(chart_entries, "Number of orders")

            val xAxisFormatter = TimestampValueFormatter()

            predictions_order_num_chart.xAxis.valueFormatter = xAxisFormatter


            predictions_order_num_chart.data = LineData(dataSet)
            predictions_order_num_chart.description.isEnabled = false
            predictions_order_num_chart.legend.isEnabled = false
            predictions_order_num_chart.setPinchZoom(true)
            predictions_order_num_chart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            predictions_order_num_chart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            predictions_order_num_chart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            predictions_order_num_chart.invalidate()

            viewModel = predictionsViewModel
            predictions_frag_rv.layoutManager = layoutManager
            predictions_frag_rv.adapter = adapter
        }
    }
}