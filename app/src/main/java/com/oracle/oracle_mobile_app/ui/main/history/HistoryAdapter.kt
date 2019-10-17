package com.oracle.oracle_mobile_app.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oracle.oracle_mobile_app.data.model.HistoryRecord
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.databinding.ItemHistoryBinding
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private lateinit var historyRecordList: List<HistoryRecord>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::historyRecordList.isInitialized) historyRecordList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyRecordList[position])
    }

    fun update(historyList: List<HistoryRecord>) {
        if(! ::historyRecordList.isInitialized)
            this.historyRecordList = listOf()
        this.historyRecordList = historyList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)
    {
        private val viewModelHR = HistoryRecordViewModel()

        fun bind(historyRecord: HistoryRecord){

            viewModelHR.bind(historyRecord)
            binding.viewModel = viewModelHR

            with(binding) {
                executePendingBindings()
            }
        }
    }
}