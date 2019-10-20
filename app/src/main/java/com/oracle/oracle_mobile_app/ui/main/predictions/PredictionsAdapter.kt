package com.oracle.oracle_mobile_app.ui.main.predictions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oracle.oracle_mobile_app.R
import com.oracle.oracle_mobile_app.data.model.AmountOfOrders
import com.oracle.oracle_mobile_app.databinding.ItemPredictionsBinding
import com.oracle.oracle_mobile_app.ui.main.history.HistoryAdapter
import kotlin.random.Random

class PredictionsAdapter : RecyclerView.Adapter<PredictionsAdapter.ViewHolder>() {


    private lateinit var ordersRecordList: List<AmountOfOrders>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionsAdapter.ViewHolder {
        val binding: ItemPredictionsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_predictions, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ordersRecordList[position])
    }

    override fun getItemCount(): Int {
        return if (::ordersRecordList.isInitialized) ordersRecordList.size else 0
    }



    fun update(ordersList: List<AmountOfOrders>){
        if(! ::ordersRecordList.isInitialized)
            this.ordersRecordList = listOf()
        this.ordersRecordList = ordersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPredictionsBinding) : RecyclerView.ViewHolder(binding.root){
        private val viewModelPR = PredictionsRecordViewModel()

        var arrayOfColors = mutableListOf<Int>()

        private fun refillArrayOfColors(){
            arrayOfColors.add( Color.rgb(192, 0, 0))
            arrayOfColors.add( Color.rgb(255, 0, 0))
            arrayOfColors.add( Color.rgb(255, 192, 0))
            arrayOfColors.add( Color.rgb(127, 127, 127))
            arrayOfColors.add( Color.rgb(146, 208, 80))
            arrayOfColors.add( Color.rgb(0, 176, 80))
            arrayOfColors.add( Color.rgb(79, 129, 189))

        }


        fun bind(predictionsRecord : AmountOfOrders) {

            viewModelPR.bind(predictionsRecord)
            binding.viewModel = viewModelPR

            with(binding){
                if(arrayOfColors.isEmpty())
                    refillArrayOfColors()

                val randInx = Random.nextInt(0,arrayOfColors.size)
                binding.predictionsItemColorLabel.setBackgroundColor(arrayOfColors[randInx])
                arrayOfColors.remove(randInx)

                executePendingBindings()
            }
        }
    }
}