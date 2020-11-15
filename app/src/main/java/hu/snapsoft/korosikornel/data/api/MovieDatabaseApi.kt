package hu.snapsoft.korosikornel.data.api

import hu.snapsoft.korosikornel.data.beans.Movie
import hu.snapsoft.korosikornel.data.beans.MovieDetail
import hu.snapsoft.korosikornel.data.beans.Movies
import hu.snapsoft.korosikornel.data.beans.Search
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseApi{

    @GET("movie/popular")
    suspend fun getPopularMovie(): Movies

    @GET("movie/{movie_id}")
    suspend fun getDetail(@Path("movie_id") id:Int): MovieDetail

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query:String): Search

}