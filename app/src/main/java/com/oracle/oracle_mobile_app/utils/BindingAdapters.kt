package com.oracle.oracle_mobile_app.utils

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.oracle.oracle_mobile_app.data.model.HistoryRecord
import com.oracle.oracle_mobile_app.ui.main.history.HistoryAdapter
import com.oracle.oracle_mobile_app.utils.extensions.getParentActivity

@BindingAdapter("historyRecordList")
fun setHistoryRecordList(recyclerView: RecyclerView, items: MutableLiveData<List<HistoryRecord>>) {
    with(recyclerView.adapter as HistoryAdapter) {
        val parentActivity: AppCompatActivity? = recyclerView.getParentActivity()
        if (parentActivity != null) {
            items.observe(parentActivity, Observer{ list ->
                this.update(list)})
        }
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}