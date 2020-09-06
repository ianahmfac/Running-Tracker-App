package id.ianahmfac.runningtrackerapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.ianahmfac.runningtrackerapp.R
import id.ianahmfac.runningtrackerapp.other.Constants.KEY_NAME
import id.ianahmfac.runningtrackerapp.other.Constants.KEY_WEIGHT
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success) {
                Snackbar.make(view, "Yay, data kamu berhasil diubah", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, "Nama dan Berat Badan diisi ya..", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPref.getString(KEY_NAME, "") ?: ""
        val weight = sharedPref.getFloat(KEY_WEIGHT, 80f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {
        val name = etName.text.toString().trim()
        val weight = etWeight.text.toString().trim()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPref.edit().putString(KEY_NAME, name).putFloat(KEY_WEIGHT, weight.toFloat()).apply()
        val toolbarText = "Semangat, $name !!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}