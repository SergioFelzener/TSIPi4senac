package br.app.pi4mobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo (val id: Int, val product_id: Int, val image: String) : Parcelable
