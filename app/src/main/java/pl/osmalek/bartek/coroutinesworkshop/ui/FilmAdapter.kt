package pl.osmalek.bartek.coroutinesworkshop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.film_item.*
import pl.osmalek.bartek.coroutinesworkshop.R
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film

class FilmAdapter(private val onClick: (Film) -> Unit) : ListAdapter<Film, FilmViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(parent)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    object DiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem.episodeId == newItem.episodeId

        override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem

    }
}

class FilmViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.film_item)), LayoutContainer {
    override val containerView = itemView

    fun bind(film: Film, onClick: (Film) -> Unit) {
        episodeIdTextView.text = film.episodeId.toString()
        nameTextView.text = film.title
        itemView.setOnClickListener { onClick(film) }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutResId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutResId, this, attachToRoot)