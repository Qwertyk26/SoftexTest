package ru.softex.feature.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.softex.model.Country
import ru.sorftex.R
import ru.sorftex.databinding.ItemCountryBinding

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    lateinit var countryList: MutableList<Country>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCountryBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_country, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int {
        return if (::countryList.isInitialized) countryList.size else 0
    }

    fun removeAt(position: Int) {
        countryList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updatePostList(countryList: MutableList<Country>) {
        this.countryList = countryList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CountryViewModel()

        fun bind(country: Country) {
            viewModel.bind(country)
            binding.viewModel = viewModel
        }
    }
}
