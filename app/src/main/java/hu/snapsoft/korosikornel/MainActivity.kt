package hu.snapsoft.korosikornel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arlib.floatingsearchview.FloatingSearchView.OnQueryChangeListener
import com.dev.adnetworkm.CheckNetworkStatus
import hu.snapsoft.korosikornel.data.adapter.MovieAdapter
import hu.snapsoft.korosikornel.data.beans.Movie
import hu.snapsoft.korosikornel.data.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter;
    private lateinit var movies: List<Movie>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setTitle(getString(R.string.main_title))

        initLayout()

    }


    private fun initLayout() {

        movies = emptyList();

        movieList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(
            movies,
            applicationContext,
            list_empty,
            { item, listPosterImg, listTitle ->
                closeKeyboard()
                openMovieDetail(
                    item,
                    listPosterImg,
                    listTitle
                )
            })
        movieList.adapter = movieAdapter;

        movieList.setOnTouchListener(OnTouchListener { v, event ->
            closeKeyboard()
            false
        })


        floating_search_view.setOnQueryChangeListener(OnQueryChangeListener { oldQuery, newQuery ->
            if (oldQuery != newQuery && newQuery.length > 2) {
                movieList.smoothScrollToPosition(0);
                searchMovies(newQuery);
            }
        })
    }


    private fun searchMovies(query: String) {
        CheckNetworkStatus.getNetworkLiveData(applicationContext).observe(this, Observer { t ->
            when (t) {
                true -> {
                    viewModel.search(query).observe(this, Observer { t ->
                        movies = t.results
                        movieAdapter.setData(movies)
                        movieAdapter.notifyDataSetChanged()
                    })
                }
                false -> {
                    Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show()
                }
                null -> {
                    // TODO: Handle the connection...
                }
            }
        })

    }

    private fun openMovieDetail(movie: Movie, listPosterImg: View, listTitle: View) {

        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra("MOVIE", movie);

        if (listPosterImg != null && ViewCompat.getTransitionName(listPosterImg) != null && listTitle != null && ViewCompat.getTransitionName(
                listTitle
            ) != null
        ) {

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, Pair(
                    listPosterImg, ViewCompat.getTransitionName(listPosterImg)
                ), Pair(listTitle, ViewCompat.getTransitionName(listTitle))
            )
            detailIntent.putExtra(
                "IMAGE_TRANSITION_NAME", ViewCompat.getTransitionName(
                    listPosterImg
                )
            );
            detailIntent.putExtra("TITLE_TRANSITION_NAME", ViewCompat.getTransitionName(listTitle));
            startActivity(detailIntent, options.toBundle())
        } else {
            startActivity(detailIntent)
        }

    }

    private fun closeKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(floating_search_view.getWindowToken(), 0)
    }

}
