package hu.snapsoft.korosikornel.data

import hu.snapsoft.korosikornel.data.api.MovieDatabaseApi
import hu.snapsoft.korosikornel.data.api.RetrofitClient
import hu.snapsoft.korosikornel.data.beans.Movie
import hu.snapsoft.korosikornel.data.beans.MovieDetail
import hu.snapsoft.korosikornel.data.beans.Movies
import hu.snapsoft.korosikornel.data.beans.Search
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow

class MovieRepository {

    var client: MovieDatabaseApi = RetrofitClient.movieApi

    @ExperimentalCoroutinesApi
    suspend fun getPopularMovies(): Movies = client.getPopularMovie()

    @ExperimentalCoroutinesApi
    suspend fun getMovieDetail(movieId: Int): MovieDetail = client.getDetail(movieId)

    @ExperimentalCoroutinesApi
    suspend fun search(query: String): Search = client.searchMovie(query)

}