package com.kuldeep.intellimedia.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuldeep.intellimedia.ui.model.TimerModel

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 25-08-2021.
 * PS: Not easy to write code, please indicate.
 */
class TimerViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<ArrayList<TimerModel>> = MutableLiveData()

    fun setTimerData(timerModel: TimerModel) {
        val arrayList = ArrayList<TimerModel>()
        arrayList.add(timerModel)
        this.mutableLiveData.value = arrayList
    }

    fun getTimerData(): MutableLiveData<ArrayList<TimerModel>> {
        return mutableLiveData
    }
}