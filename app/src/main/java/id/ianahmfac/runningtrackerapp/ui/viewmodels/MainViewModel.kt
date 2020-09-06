package id.ianahmfac.runningtrackerapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ianahmfac.runningtrackerapp.db.Run
import id.ianahmfac.runningtrackerapp.other.SortType
import id.ianahmfac.runningtrackerapp.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    val runs = MediatorLiveData<List<Run>>()
    private val runSortedByDate = mainRepository.getAllRunsByDate()
    private val runSortedByRunningTime = mainRepository.getAllRunsByRunningTime()
    private val runSortedBySpeed = mainRepository.getAllRunsBySpeed()
    private val runSortedByDistance = mainRepository.getAllRunsByDistance()
    private val runSortedByCalories = mainRepository.getAllRunsByCalories()

    var sortType = SortType.DATE

    init {
        runs.addSource(runSortedByDate) { result ->
            if (sortType == SortType.DATE) {
                runs.value = result
            }
        }
        runs.addSource(runSortedByRunningTime) { result ->
            if (sortType == SortType.RUNNING_TIME) {
                runs.value = result
            }
        }
        runs.addSource(runSortedByCalories) { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                runs.value = result
            }
        }
        runs.addSource(runSortedBySpeed) { result ->
            if (sortType == SortType.AVG_SPEED) {
                runs.value = result
            }
        }
        runs.addSource(runSortedByDistance) { result ->
            if (sortType == SortType.DISTANCE) {
                runs.value = result
            }
        }
    }

    fun sortRuns(sortType: SortType) = when (sortType) {
        SortType.DATE -> runSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runSortedByRunningTime.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runSortedByCalories.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runSortedBySpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runSortedByDistance.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}