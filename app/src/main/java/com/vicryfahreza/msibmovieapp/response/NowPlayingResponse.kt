import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int?,
    val overview: String?,
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backdropPath)
        parcel.writeValue(id)
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeValue(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResponse> {
        override fun createFromParcel(parcel: Parcel): MovieResponse {
            return MovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class NowPlayingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        arrayListOf<MovieResponse>().apply {
            parcel.readList(this, MovieResponse::class.java.classLoader)
        },
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeList(results)
        parcel.writeInt(totalPage)
        parcel.writeInt(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NowPlayingResponse> {
        override fun createFromParcel(parcel: Parcel): NowPlayingResponse {
            return NowPlayingResponse(parcel)
        }

        override fun newArray(size: Int): Array<NowPlayingResponse?> {
            return arrayOfNulls(size)
        }
    }
}
