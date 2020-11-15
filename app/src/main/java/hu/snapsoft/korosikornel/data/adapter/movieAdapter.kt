package hu.snapsoft.korosikornel.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.snapsoft.korosikornel.BuildConfig
import hu.snapsoft.korosikornel.R
import hu.snapsoft.korosikornel.data.beans.Movie
import kotlinx.android.synthetic.main.list_movie.view.*

internal class MovieAdapter(
    private var arrayList: List<Movie>,
    private val context: Context,
    private val emptyView: TextView,
    private val listener: (Movie,View,View) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
        holder.itemView.list_cardview.setOnClickListener { listener(arrayList[position],holder.itemView.listPosterImg,holder.itemView.listTitle) }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setData(data: List<Movie>){
        arrayList=data
        if(arrayList.isEmpty()){
            emptyView.context.getString(R.string.don_t_find_item_for_this_search)
            emptyView.visibility  = View.VISIBLE
        }
        else{
            emptyView.context.getString(R.string.start_type_on_searchbar)
            emptyView.visibility  = View.GONE
        }
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(items: Movie) {
            ViewCompat.setTransitionName(itemView.listPosterImg, items.posterPath);
            ViewCompat.setTransitionName(itemView.listTitle, items.title);

            itemView.listTitle.text = items.title
            itemView.listRate.text = "Rate: "+items.voteAverage.toString()

            Picasso.get().load(BuildConfig.POSTER_BIG_URL + items.posterPath).placeholder(R.drawable.ic_movidb_placeholder).error(R.drawable.ic_movidb_placeholder).into(
                itemView.listPosterImg
            );



        }
    }
}