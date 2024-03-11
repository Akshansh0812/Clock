package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener{
            playAndStopTimer()
        }
        binding.pauseButton.setOnClickListener{
//            pauseTimer()
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
//    private fun pauseTimer(){
//        if(isRunning){
//            handler.removeCallbacks(runnable)
//            isRunning = false
//
//            binding.pauseButton.setImageResource(R.drawable.ic_play)
//        }
//    }
    private fun resetTimer(){

        timerSecond =  0
        binding.timerTextView.text = "00:00:00"

        binding.playButton.visibility = View.VISIBLE
        binding.pauseButton.visibility = View.GONE
        binding.resetButton.visibility = View.GONE
    }
}