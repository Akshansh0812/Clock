package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stopwatch.databinding.FragmentTimerBinding



class TimerFragment : Fragment() {

    private lateinit var binding : FragmentTimerBinding
    private var isRunning = false
    private var timerSecond = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSecond++
            val hours = timerSecond / 3600
            val minutes = (timerSecond % 3600) / 60
            val seconds = timerSecond % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timerTextView.text = time

            handler.postDelayed(this, 1000)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTimerBinding.inflate(inflater)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playButton.setOnClickListener{
            playAndStopTimer()
        }
        binding.pauseButton.setOnClickListener{
            playAndStopTimer()
        }
        binding.resetButton.setOnClickListener{
            resetTimer()
        }

    }
    private fun playAndStopTimer(){
        if(!isRunning){
            handler.postDelayed(runnable,1000)
            isRunning = true

            binding.playButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            binding.resetButton.visibility = View.VISIBLE
            binding.pauseButton.setImageResource(R.drawable.ic_pause)
        }
        else{
            if(isRunning){
                handler.removeCallbacks(runnable)
                isRunning = false

                binding.pauseButton.setImageResource(R.drawable.ic_play)
            }
        }
    }
    private fun resetTimer(){

        timerSecond =  0
        binding.timerTextView.text = "00:00:00"

        binding.playButton.visibility = View.VISIBLE
        binding.pauseButton.visibility = View.GONE
        binding.resetButton.visibility = View.GONE
    }
}
