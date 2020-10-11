package com.dodemy.bottomsheet

import android.app.Dialog
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

override fun setupDialog(dialog: Dialog, style: Int) {
    super.setupDialog(dialog, style)
    val rootView = View.inflate(context, R.layout.dialog_layout, null)
    dialog.setContentView(rootView)

    val bottomSheet = dialog.window?.findViewById(R.id.design_bottom_sheet) as FrameLayout
    val behaviour = BottomSheetBehavior.from(bottomSheet)

    behaviour.peekHeight = 0

}
