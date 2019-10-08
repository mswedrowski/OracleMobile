package com.oracle.oracle_mobile_app.base

import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity  : DaggerAppCompatActivity()
{
    abstract val viewModel: BaseViewModel
    private lateinit var binding: ViewDataBinding



    protected val onPauseDisposables = CompositeDisposable()
    protected val onStopDisposables = CompositeDisposable()
    protected val onDestroyDisposables = CompositeDisposable()



    override fun onPause() {
        onPauseDisposables.clear()
        super.onPause()
    }

    override fun onStop() {
        onStopDisposables.clear()
        super.onStop()
    }

    override fun onDestroy() {
        onDestroyDisposables.clear()
        super.onDestroy()
    }
}