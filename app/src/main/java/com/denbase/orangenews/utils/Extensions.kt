package com.denbase.orangenews.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.ImageView
import androidx.annotation.StyleRes
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder


//Visibility changes
fun View.hideView(){
    this.visibility = View.GONE
}

fun View.showView(){
    this.visibility = View.VISIBLE
}


//AlertDialog Builder
fun Context.alert(
    @StyleRes style: Int = 0,
    dialogBuilder: MaterialAlertDialogBuilder.() -> Unit
) {
    MaterialAlertDialogBuilder(this, style)
        .apply {
            setCancelable(false)
            dialogBuilder()
            create()
            show()
        }
}
fun MaterialAlertDialogBuilder.neutralButton(
    text: String = "OK",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

//Image Download
fun  ImageView.downloadImage(url: String?){
    Glide.with(this).load(url).centerCrop().into(this)
}