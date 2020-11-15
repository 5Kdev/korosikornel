package hu.snapsoft.korosikornel.data.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Search (
    @SerializedName("page")
    @Expose
    val page:Int = 0,

    @SerializedName("results")
    @Expose
    val results: List<Movie> = ArrayList(),

    @SerializedName("total_results")
    @Expose
    val totalResults:Int = 0,

    @SerializedName("total_pages")
    @Expose
    val totalPages:Int = 0
)