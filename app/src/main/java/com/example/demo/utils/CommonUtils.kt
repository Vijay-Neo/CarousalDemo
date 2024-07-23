package com.example.demo.utils

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.example.demo.R
import com.google.android.material.bottomsheet.BottomSheetDialog

object CommonUtils {

    fun showBottomSheetDialog(
        context: Activity,
        itemCount: Int?=0,
        top3Chars: List<MutableMap.MutableEntry<Char, Int>>?) {
        val dialogView = context.layoutInflater.inflate(R.layout.bottomsheet_dialog, null)
        val itemCountTextView = dialogView.findViewById<TextView>(R.id.itemCountTextView)
        val topCharactersTextView = dialogView.findViewById<TextView>(R.id.topCharactersTextView)

        if(itemCount != 0) {
            val topCharacters = top3Chars?.joinToString("\n") { "${it.key} = ${it.value}" }
            itemCountTextView.text = context.getString(R.string.item_count, itemCount)
            topCharactersTextView.text = context.getString(R.string.top_characters, topCharacters)
        }else{
            itemCountTextView.text = context.getString(R.string.no_data)
            topCharactersTextView.visibility =  View.INVISIBLE
        }

        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.show()
    }
}