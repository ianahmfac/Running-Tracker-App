package id.ianahmfac.runningtrackerapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ianahmfac.runningtrackerapp.db.RunDatabase
import id.ianahmfac.runningtrackerapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import id.ianahmfac.runningtrackerapp.other.Constants.KEY_NAME
import id.ianahmfac.runningtrackerapp.other.Constants.KEY_WEIGHT
import id.ianahmfac.runningtrackerapp.other.Constants.RUN_DATABASE_NAME
import id.ianahmfac.runningtrackerapp.other.Constants.SHARED_PREFERENCES_NAME
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RunDatabase::class.java, RUN_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunDatabase) = db.runDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )
}