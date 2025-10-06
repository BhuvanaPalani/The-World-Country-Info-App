package se.linerotech.module202.lecture.countryDetail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import kotlinx.coroutines.launch
import se.linerotech.module202.lecture.R

class CountryDetailActivity : AppCompatActivity() {

    private val viewModel: CountryDetailViewModel by viewModels { CountryDetailViewModel.factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_country_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Views
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        val imageViewFlag = findViewById<ImageView>(R.id.imageViewFlag)
        val textViewCountryName = findViewById<TextView>(R.id.textViewCountryName)
        val textViewRegion = findViewById<TextView>(R.id.textViewRegion)
        val textViewPopulationValue = findViewById<TextView>(R.id.textViewPopulationValue)
        val textViewAreaValue = findViewById<TextView>(R.id.textViewAreaValue)
        val textViewCapitalValue = findViewById<TextView>(R.id.textViewCapitalValue)
        val textViewCallingCodeValue = findViewById<TextView>(R.id.textViewCallingCodeValue)
        val textViewTldValue = findViewById<TextView>(R.id.textViewTldValue)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        imageViewBack.setOnClickListener { finish() }

        // Observe state
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is CountryDetailState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            textViewError.visibility = View.GONE
                        }

                        is CountryDetailState.Success -> {
                            progressBar.visibility = View.GONE
                            textViewError.visibility = View.GONE

                            val detail = state.detail // <-- 'data' per the updated ViewModel
                            imageViewFlag.load(detail.flagUrl) { crossfade(true) } // load URL with Coil
                            textViewCountryName.text = detail.name
                            textViewRegion.text = detail.region
                            textViewPopulationValue.text = detail.population
                            textViewAreaValue.text = detail.area
                            textViewCapitalValue.text = detail.capital
                            textViewCallingCodeValue.text = detail.callingCode
                            textViewTldValue.text = detail.topLevelDomain
                        }

                        is CountryDetailState.Failure -> {
                            progressBar.visibility = View.GONE
                            textViewError.visibility = View.VISIBLE
                            textViewError.text = state.message
                        }
                    }
                }
            }
        }

        // Load data from intent
        val countryName = intent.getStringExtra("countryName") ?: "Unknown Country"
        viewModel.loadDummyData(countryName) // now fetches real data
    }
}
