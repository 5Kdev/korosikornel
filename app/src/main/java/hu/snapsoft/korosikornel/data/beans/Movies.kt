package hu.snapsoft.korosikornel.data.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Movies(
    @SerializedName("page")
    @Expose
    val page: Int = 0,

    @SerializedName("results")
    val results: List<Movie> = ArrayList(),

    @SerializedName("total_results")
    val totalResults: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0
)