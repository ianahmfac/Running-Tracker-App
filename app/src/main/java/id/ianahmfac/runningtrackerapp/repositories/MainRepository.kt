package id.ianahmfac.runningtrackerapp.repositories

import id.ianahmfac.runningtrackerapp.db.Run
import id.ianahmfac.runningtrackerapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val runDao: RunDao) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsByDate() = runDao.getAllRunsByDate()
    fun getAllRunsByRunningTime() = runDao.getAllRunsByRunningTime()
    fun getAllRunsBySpeed() = runDao.getAllRunsBySpeed()
    fun getAllRunsByDistance() = runDao.getAllRunsByDistance()
    fun getAllRunsByCalories() = runDao.getAllRunsByCalories()

    fun getTotalTimesInMillis() = runDao.getTotalTimesInMillis()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()
}