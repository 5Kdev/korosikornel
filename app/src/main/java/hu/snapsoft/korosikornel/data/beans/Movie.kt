package hu.snapsoft.korosikornel.data.beans

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,

    @SerializedName("adult")
    @Expose
    val adult: Boolean = false,

    @SerializedName("overview")
    @Expose
    val overview: String? = null,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,


    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("budget")
    @Expose
    val budget: Int = 0,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,

    @SerializedName("popularity")
    @Expose
    val popularity: Float = 0.0f,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int = 0,

    @SerializedName("video")
    @Expose
    val video: Boolean = false,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float = 0.0f
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeInt(id)
        parcel.writeInt(budget)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeFloat(popularity)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeFloat(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}