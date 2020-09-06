package id.ianahmfac.runningtrackerapp.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.ianahmfac.runningtrackerapp.R

class CancelTrackingDialog : DialogFragment() {
    private var yesListener: (() -> Unit)? = null

    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Batalkan aktivitas?")
            .setMessage("Batalkan aktivitas berlari dan menghapus seluruh data ini?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Ya") { _, _ ->
                yesListener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton("Tidak") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}