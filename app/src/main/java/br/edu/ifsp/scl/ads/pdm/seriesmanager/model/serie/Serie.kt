package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Serie(
<<<<<<< HEAD
    val nomeSerie: String="",
    val anoLancamentoSerie: String="",
    val emissoraSerie: String="",
    val generoSerie: String=""
=======
    val nomeSerie: String,
    val anoLancamentoSerie: String,
    val emissoraSerie: String,
    val generoSerie: String
>>>>>>> 4505eb7479f9a49480b258f4df4e9d01ca3e9808
): Parcelable
