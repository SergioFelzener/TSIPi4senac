package br.app.pi4mobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category  (val id: Int, val name: String, val description: String) : Parcelable