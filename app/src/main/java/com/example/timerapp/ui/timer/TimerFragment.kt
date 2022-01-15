package com.example.timerapp.ui.timer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.timerapp.R
import com.example.timerapp.databinding.TimerFragmentBinding
import android.os.Handler
import android.os.SystemClock
import com.example.timerapp.helpers.SessionManager
import com.example.timerapp.viewmodel.TimerViewModel


class TimerFragment : Fragment(R.layout.timer_fragment) {

    private lateinit var binding: TimerFragmentBinding
    private lateinit var viewModel: TimerViewModel

    var MillisecondTime: Long = 0L
    var StartTime: Long = 0L
    var TimeBuff: Long = 0L
    var UpdateTime: Long = 0L

    var time = ""
    var timems = 0L

    var handler: Handler? = null

    var Seconds = 0
    var Minutes:Int = 0
    var Hours:Int = 0
    var MilliSeconds:Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SessionManager.context = requireContext()
        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        binding = TimerFragmentBinding.bind(view)
        binding.userLoginDate.setText("Logged in: " + SessionManager.userLoginDate)
        handler = Handler()
        startTimer()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun startTimer() {
        StartTime = SystemClock.uptimeMillis();
        handler?.postDelayed(runnable, 0);
    }

    var runnable: Runnable = object : Runnable {
        override fun run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime

            if(SessionManager.userTime != null && SessionManager.userTime != 0L){
                UpdateTime = SessionManager.userTime!! + MillisecondTime
            }else{
                UpdateTime = TimeBuff + MillisecondTime
            }
            Seconds = (UpdateTime / 1000).toInt() //1 s --> 1000ms
            Minutes = Seconds / 60   //1 m --> 60000ms
            Hours = Minutes / 60     //1 h --> 3600000ms
            timems = (UpdateTime)
            Seconds = Seconds % 60
            MilliSeconds = (UpdateTime % 1000).toInt()

            time = ("" + String.format("%02d", Hours) + ":"
                    + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds))
            binding.timerClock.setText(time)
            handler?.postDelayed(this, 0);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SessionManager.userTime = timems
        handler?.removeCallbacks(runnable)
    }

}