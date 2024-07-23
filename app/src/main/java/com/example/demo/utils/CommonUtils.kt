package com.example.demo.utils

import android.app.Activity
import android.view.View
import com.example.demo.R
import com.example.demo.databinding.BottomsheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

object CommonUtils {

    fun showBottomSheetDialog(
        context: Activity,
        itemCount: Int? = 0,
        top3Chars: List<MutableMap.MutableEntry<Char, Int>>? = null
    ) {
        val dialogBinding = BottomsheetDialogBinding.inflate(context.layoutInflater)

        if (itemCount != 0) {
            val topCharacters = top3Chars?.joinToString("\n") { "${it.key} = ${it.value}" }
            dialogBinding.itemCountTextView.text = context.getString(R.string.item_count, itemCount)
            dialogBinding.topCharactersTextView.text = context.getString(R.string.top_characters, topCharacters)
        } else {
            dialogBinding.itemCountTextView.text = context.getString(R.string.no_data)
            dialogBinding.topCharactersTextView.visibility = View.INVISIBLE
        }

        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(dialogBinding.root)
        bottomSheetDialog.show()
    }
}