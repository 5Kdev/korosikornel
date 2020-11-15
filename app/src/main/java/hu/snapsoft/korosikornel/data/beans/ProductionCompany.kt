package hu.snapsoft.korosikornel.data.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
)