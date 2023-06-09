package com.example.moviesapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id:String?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("poster_path")
    val poster:String?,

    @SerializedName("release_date")
    val release:String?,

    @SerializedName("vote_average")
    val voteavg:String?,

    @SerializedName("backdrop_path")
    val backdrop:String?,

    @SerializedName("overview")
    val overview:String?

    ) : Parcelable {
    constructor():this("","","","","","","")
}
