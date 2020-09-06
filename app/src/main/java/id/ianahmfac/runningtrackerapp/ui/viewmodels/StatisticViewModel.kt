package id.ianahmfac.runningtrackerapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import id.ianahmfac.runningtrackerapp.repositories.MainRepository

class StatisticViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    val totalTimeRun = mainRepository.getTotalTimesInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runSortedByDate = mainRepository.getAllRunsByDate()
}