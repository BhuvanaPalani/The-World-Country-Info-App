package se.linerotech.module202.lecture.countryList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import se.linerotech.module202.lecture.R
import se.linerotech.module202.lecture.countryDetail.CountryDetailActivity

class CountryListActivity : AppCompatActivity() {

    private lateinit var recyclerViewCountries: RecyclerView
    private lateinit var adapter: CountryRecyclerViewAdapter

    private val viewModel: CountryViewModel by viewModels { CountryViewModel.factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        recyclerViewCountries = findViewById(R.id.recyclerViewCountries)
        adapter = CountryRecyclerViewAdapter { selected ->
            val intent = Intent(this, CountryDetailActivity::class.java)
            intent.putExtra("countryName", selected.name)
            startActivity(intent)
        }

        recyclerViewCountries.layoutManager = LinearLayoutManager(this)
        recyclerViewCountries.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 1) collect countries (update the list)
                launch {
                    viewModel.state.collect { countries ->
                        adapter.updateItems(countries)
                    }
                }
                // 2) collect errors (show a toast)
                launch {
                    viewModel.error.collect { msg ->
                        msg?.let {
                            Toast.makeText(this@CountryListActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
