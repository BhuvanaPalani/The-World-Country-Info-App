package se.linerotech.module202.lecture.countryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import se.linerotech.module202.lecture.common.Country
import se.linerotech.module202.lecture.databinding.ItemCountryBinding

class CountryRecyclerViewAdapter(
    private var items: List<Country> = emptyList(),
    private val onCellClicked: (Country) -> Unit
) : RecyclerView.Adapter<CountryRecyclerViewAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.create(parent, onCellClicked)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = items[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Country>) {
        items = newItems
        notifyDataSetChanged()
    }

    class CountryViewHolder(
        private val binding: ItemCountryBinding,
        private val onCellClicked: (Country) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.imageViewFlag.load(country.flagUrl) {
                crossfade(true)
            }
            binding.textViewCountryName.text = country.name
            binding.root.setOnClickListener { onCellClicked(country) }
        }

        companion object {
            fun create(parent: ViewGroup, onCellClicked: (Country) -> Unit): CountryViewHolder {
                val binding = ItemCountryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CountryViewHolder(binding, onCellClicked)
            }
        }
    }
}
