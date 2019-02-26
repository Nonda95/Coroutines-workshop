package pl.osmalek.bartek.coroutinesworkshop.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.person_item.*
import pl.osmalek.bartek.coroutinesworkshop.R
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person

class PeopleAdapter : ListAdapter<Person, PersonViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem
    }
}

class PersonViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.person_item)), LayoutContainer {
    override val containerView = itemView

    fun bind(person: Person) {
        nameTextView.text = person.name
        homeWorldTextView.text = person.homeWorld
    }
}