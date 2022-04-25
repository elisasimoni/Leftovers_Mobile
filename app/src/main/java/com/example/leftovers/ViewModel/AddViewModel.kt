package com.example.progetto.ViewModel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


/**
 * ViewModel that handles the data flow between the MainActivity and its Fragment
 */
class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val imageBitmap = MutableLiveData<Bitmap>()



}