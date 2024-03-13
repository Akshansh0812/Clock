package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stopwatch.databinding.FragmentClockBinding
import java.text.SimpleDateFormat
import java.util.*


class ClockFragment : Fragment() {

    private lateinit var binding : FragmentClockBinding

    private val handler = Handler(Looper.getMainLooper())
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentClockBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateTimeEverySecond()
    }
    private fun updateTimeEverySecond() {
        handler.post(object : Runnable {
            override fun run() {
                binding.timeTextView.text = timeFormat.format(Calendar.getInstance().time)
                binding.dateTextView.text = dateFormat.format(Calendar.getInstance().time)
                handler.postDelayed(this, 1000)
            }
        })
    }
}