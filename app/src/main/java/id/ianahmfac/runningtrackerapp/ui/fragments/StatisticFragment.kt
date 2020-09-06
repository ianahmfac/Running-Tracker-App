package id.ianahmfac.runningtrackerapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import id.ianahmfac.runningtrackerapp.R
import id.ianahmfac.runningtrackerapp.other.CustomMarkerView
import id.ianahmfac.runningtrackerapp.other.RunningUtility
import id.ianahmfac.runningtrackerapp.ui.viewmodels.StatisticViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlin.math.round

@AndroidEntryPoint
class StatisticFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObserver()
        setupBarChart()
    }

    private fun setupBarChart() {
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.axisLeft.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.apply {
            description.text = "Kecepatan rata-rata dari waktu ke waktu"
            legend.isEnabled = false
        }
    }

    private fun subscribeToObserver() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTimeRun = RunningUtility.getFormattedStopwatchTime(it)
                tvTotalTime.text = totalTimeRun
            }
        })

        viewModel.totalDistance.observe(viewLifecycleOwner, Observer {
            it?.let {
                val km = it / 1000f
                val totalDistance = round(km * 10f) / 10f
                val totalDistanceString = "${totalDistance}km"
                tvTotalDistance.text = totalDistanceString
            }
        })

        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
            it?.let {
                val avgSpeed = round(it * 10f) / 10f
                val avgSpeedString = "${avgSpeed}km/h"
                tvAverageSpeed.text = avgSpeedString
            }
        })

        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalCalories = "${it}kcal"
                tvTotalCalories.text = totalCalories
            }
        })

        viewModel.runSortedByDate.observe(viewLifecycleOwner, Observer {
            it?.let {
                val allAvgSpeed = it.indices.map { i -> BarEntry(i.toFloat(), it[i].avgSpeedInKMH) }
                val barDataSet =
                    BarDataSet(allAvgSpeed, "Kecepatan rata-rata dari waktu ke waktu").apply {
                        valueTextColor = Color.WHITE
                        color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                    }
                barChart.data = BarData(barDataSet)
                barChart.marker =
                    CustomMarkerView(it.reversed(), requireContext(), R.layout.marker_view)
                barChart.invalidate()
            }
        })
    }
}