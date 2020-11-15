package hu.snapsoft.korosikornel.data.viewmodels


import android.util.Log
import hu.snapsoft.korosikornel.data.beans.Movies
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hu.snapsoft.korosikornel.data.MovieRepository
import kotlinx.coroutines.flow.*
import java.io.IOException

class MovieViewModel : ViewModel() {
    val repository: MovieRepository = MovieRepository();

    fun loadPopularMovies() = liveData {
        try {
            val receivedData = repository.getPopularMovies()
            emit(receivedData)
        } catch (e: IOException) {
            Log.d("loadPopularMovies", e.toString())
        } catch (e: Exception) {
            Log.d("loadPopularMovies", e.toString())
        }
    }

    fun loadDetail(movieId: Int) = liveData {
        try {
            Log.d("LOAD", "loadDetail")

            val detailData = repository.getMovieDetail(movieId)
            emit(detailData)
        } catch (e: IOException) {
            Log.d("loadDetail", e.toString())
        } catch (e: Exception) {
            Log.d("loadDetail", e.toString())
        }
    }

    fun search(query: String) = liveData {
        try {
            val searchData = repository.search(query)
            emit(searchData)
        } catch (e: IOException) {
            Log.d("loadDetail", e.toString())
        } catch (e: Exception) {
            Log.d("loadDetail", e.toString())
        }
    }

}
