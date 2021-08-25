package com.kuldeep.intellimedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.intellimedia.customUI.swipeToDelete.RecyclerTouchListener
import com.kuldeep.intellimedia.ui.adapter.TimerAdapter
import com.kuldeep.intellimedia.ui.model.TimerModel
import com.kuldeep.intellimedia.ui.viewModel.TimerViewModel
import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity : AppCompatActivity(), View.OnClickListener,
        TimerAdapter.TimerAdapterClickListener {

    private lateinit var timerModel: TimerModel
    private lateinit var TimerViewModel: TimerViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var touchListener: RecyclerTouchListener? = null
    private var arrayList: ArrayList<TimerModel>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        supportActionBar?.hide()

        initView()
        initLister()
    }

    private fun initView() {
        toolbar.setTitle(R.string.app_name)
        rvTimer.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvTimer.layoutManager = layoutManager

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
                if (editEnterSeconds?.text.toString().trim().isNotEmpty()) {
                    timerModel = TimerModel(
                            timer = editEnterSeconds.text.toString(),
                            timerId = arrayList?.size
                    )
                    arrayList?.add(timerModel)
                    (rvTimer.adapter as TimerAdapter).updateList(arrayList!!)

                    //no data check
                    noDataShow(arrayList!!)

                    Toast.makeText(
                            applicationContext,
                            getString(R.string.start),
                            Toast.LENGTH_SHORT
                    ).show()

                    editEnterSeconds.text?.clear()
                } else {
                    Toast.makeText(this, getString(R.string.enter_seconds), Toast.LENGTH_SHORT)
                            .show()
                }
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
        if (position == 0) {
            arrayList?.removeAt(position)
            noDataShow(arrayList!!)
        } else {
            (rvTimer.adapter as TimerAdapter).removeItem(position)
            arrayList?.removeAt(position)
            noDataShow(arrayList!!)
        }
    }
}