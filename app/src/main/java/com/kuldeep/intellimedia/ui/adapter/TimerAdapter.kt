package com.kuldeep.intellimedia.ui.adapter

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.intellimedia.R
import com.kuldeep.intellimedia.ui.model.TimerModel
import kotlinx.android.synthetic.main.item_timer_layout.view.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 25-08-2021.
 * PS: Not easy to write code, please indicate.
 */

class TimerAdapter(
    arrayList: ArrayList<TimerModel>,
    context: Context,
    private val listener: TimerAdapterClickListener
) :
    RecyclerView.Adapter<TimerAdapter.ViewHolder>() {

    private var ctx: Context? = context
    private var timerList: List<TimerModel> = arrayList.toList()
    private var mTimerRunning = false
    private var mTimeLeftInMillis: Long? = null
    private var mCountDownTimer: CountDownTimer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timer_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timerModel: TimerModel = timerList[position]
        holder.bindItems(position, timerModel, listener)
    }

    override fun getItemCount(): Int {
        return timerList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(
            position: Int,
            timerModel: TimerModel,
            listenerMessages: TimerAdapterClickListener
        ) {

            val clView = itemView.clMain
            with(clView) {
                /*mTimeLeftInMillis = TimeUnit.SECONDS.toMillis(timerModel.timer!!.toLong())
                startTimer(txtTimer, position)*/
                txtTimer.text = timerModel.timer
                btnTimer.setOnClickListener {
                    /*if (mTimerRunning) {
                        btnTimer.text = ctx?.getString(R.string.pause)
                        mTimerRunning = false
                        pauseTimer()
                    } else {
                        btnTimer.text = ctx?.getString(R.string.resume)
                        mTimerRunning = true
                        startTimer(txtTimer, position)
                    }*/
                }
            }

        }
    }

    /*private fun updateCountDownText(textView: AppCompatTextView) {
        val hms = String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis!!),
            TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis!!) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    mTimeLeftInMillis!!
                )
            ), TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis!!) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis!!)
            )
        )
        textView.text = hms //set text
    }*/

    /*private fun startTimer(textView: AppCompatTextView, position: Int) {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText(textView)
            }

            override fun onFinish() {
                mTimerRunning = false
                listener.onSelectTimer(position = position)
            }
        }.start()
        mTimerRunning = true
    }

    private fun pauseTimer() {
        mCountDownTimer!!.cancel()
        mTimerRunning = false
    }*/


    fun removeItem(position: Int) {
        val arrayList = ArrayList<TimerModel>(timerList)
        arrayList.removeAt(position)
        notifyDataSetChanged()
        timerList = arrayList
        notifyItemRemoved(position)
    }


    fun updateList(list: java.util.ArrayList<TimerModel>) {
        timerList = list
        notifyDataSetChanged()
    }

    interface TimerAdapterClickListener {
        fun onSelectTimer(
            position: Int
        )
    }
}