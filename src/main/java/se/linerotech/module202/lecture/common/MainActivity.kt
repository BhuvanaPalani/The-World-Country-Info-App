package se.linerotech.module202.lecture.common

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import se.linerotech.module202.lecture.R
import se.linerotech.module202.lecture.countryList.CountryListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGetStarted = findViewById<Button>(R.id.buttonGetStarted)
        buttonGetStarted.setOnClickListener {
            startActivity(Intent(this, CountryListActivity::class.java))
            finish()
        }
    }
}
