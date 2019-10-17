package com.oracle.oracle_mobile_app.ui.main.predictions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oracle.oracle_mobile_app.R

class PredictionsFragment : Fragment() {

    private lateinit var predictionsViewModel: PredictionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        predictionsViewModel =
            ViewModelProviders.of(this).get(PredictionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_predictions, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        predictionsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}