@file:Suppress("DEPRECATION")

package com.kuldeep.intellimedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.intellimedia.customUI.swipeToDelete.RecyclerTouchListener
import com.kuldeep.intellimedia.ui.adapter.TimerAdapter
import com.kuldeep.intellimedia.ui.model.TimerModel
import com.kuldeep.intellimedia.ui.viewModel.TimerViewModel
import kotlinx.android.synthetic.main.activity_timer.*

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 25-08-2021.
 * PS: Not easy to write code, please indicate.
 */
class TimerActivity : AppCompatActivity(), View.OnClickListener,
    TimerAdapter.TimerAdapterClickListener {

    private lateinit var timerModel: TimerModel
    private lateinit var timerViewModel: TimerViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var touchListener: RecyclerTouchListener? = null
    private var arrayList: ArrayList<TimerModel>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        supportActionBar?.hide()

        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)

        initView()
        initLister()
    }

    private fun initView() {
        toolbar.setTitle(R.string.app_name)
        rvTimer.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvTimer.layoutManager = layoutManager

        timerViewModel.getTimerData().observe(this, {
            (rvTimer.adapter as TimerAdapter).updateList(it)
            for (item in it) {
                arrayList?.add(item)
            }
            //no data check
            noDataShow(arrayList!!)
        })

        rvTimer.adapter = TimerAdapter(
            context = this,
            arrayList = arrayList!!,
            listener = this
        )

        //no data check
        noDataShow(arrayList!!)

        //swipe to Delete call
        recyclerViewSwipeController(rvTimer, rvTimer.adapter as TimerAdapter)
    }

    private fun recyclerViewSwipeController(recyclerView: RecyclerView, adapter: TimerAdapter) {
        touchListener = RecyclerTouchListener(this, recyclerView)
        touchListener!!.setClickable(object : RecyclerTouchListener.OnRowClickListener {
            override fun onRowClicked(position: Int) {
            }

            override fun onIndependentViewClicked(independentViewID: Int, position: Int) {
            }
        })
            .setSwipeOptionViews(R.id.imgDelete)
            .setSwipeable(R.id.clMain, R.id.clDelete) { viewID, position ->
                when (viewID) {
                    R.id.imgDelete -> {
                        adapter.removeItem(position)
                        arrayList?.removeAt(position)
                        noDataShow(arrayList!!)
                    }
                }
            }
        recyclerView.addOnItemTouchListener(touchListener!!)
    }

    private fun initLister() {

        btnStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStart -> {
                hideKeyboard(this)

                timerModel = TimerModel(
                    timer = editEnterSeconds.text.toString(),
                    timerId = arrayList?.size
                )
                timerViewModel.setTimerData(timerModel)

                Toast.makeText(
                    applicationContext,
                    "${getString(R.string.start)} Postiton ${arrayList?.size} Data ${timerModel.timer}",
                    Toast.LENGTH_SHORT
                ).show()

                editEnterSeconds.text?.clear()
            }
        }
    }

    private fun noDataShow(arrayList: ArrayList<TimerModel>) {
        if (arrayList.size > 0) {
            rvTimer.visibility = View.VISIBLE
            txtNoData.visibility = View.INVISIBLE
        } else {
            rvTimer.visibility = View.INVISIBLE
            txtNoData.visibility = View.VISIBLE
        }
    }

    override fun onSelectTimer(position: Int) {
        (rvTimer.adapter as TimerAdapter).removeItem(position)
        arrayList?.removeAt(position)
        noDataShow(arrayList!!)
    }
}