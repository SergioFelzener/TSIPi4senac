package br.app.pi4mobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val descount: String, //discount?
    val quantity: String,
    val slug: String,
    val photos: List<Photo>? = null

): Parcelable
