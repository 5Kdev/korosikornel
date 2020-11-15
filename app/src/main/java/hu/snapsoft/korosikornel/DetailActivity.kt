package hu.snapsoft.korosikornel

import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dev.adnetworkm.CheckNetworkStatus
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import hu.snapsoft.korosikornel.data.beans.Movie
import hu.snapsoft.korosikornel.data.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    val viewModel: MovieViewModel by viewModels()
    lateinit var item:Movie;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDetail()

        item = intent.extras!!.getParcelable<Movie>("MOVIE")!!

        val image_transition: String? = intent.getSerializableExtra("IMAGE_TRANSITION_NAME") as? String
        val title_transition: String? = intent.getSerializableExtra("TITLE_TRANSITION_NAME") as? String

        if(image_transition!=null&&title_transition!=null){
            detailPoster.setTransitionName(image_transition);
            detailTitle.setTransitionName(title_transition);
        }

        detailTitle.text = item!!.title

        Picasso.get()
            .load(BuildConfig.POSTER_BIG_URL + item.posterPath)
            .noFade()
            .placeholder(R.drawable.ic_movidb_placeholder)
            .error(R.drawable.ic_movidb_placeholder)
            .into(detailPoster, object: com.squareup.picasso.Callback {
                override fun onSuccess() {
                   supportStartPostponedEnterTransition()
                }
                override fun onError(e: java.lang.Exception?) {
                }
            })
    }

    fun loadDetail(){
            CheckNetworkStatus.getNetworkLiveData(applicationContext).observe(this, Observer { t ->
                when (t) {
                    true -> {
                        viewModel.loadDetail(item.id).observe(this, Observer { t ->
                            detailRelease.text="Release date: "+t.releaseDate
                           var genres:String = "Genres: "
                            t.genres.forEach { it ->
                                genres +=it.name+", "
                            }
                            detailGenre.text=genres
                            detailBudget.text="Budget: "+t.budget.toString()
                            detailDescription.text=t.overview
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) {
            //to reverse the scene transition animation
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        super.onBackPressed()
    }
    


}