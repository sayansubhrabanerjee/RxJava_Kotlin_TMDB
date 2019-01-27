package com.example.h260210.kotmovies_prac_1.extensions

import android.content.Context
import android.widget.Toast

fun Context.displayToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}