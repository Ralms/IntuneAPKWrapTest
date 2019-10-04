package com.ralmworld.sharedprefencesrepro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var flagValue = sharedPref.getBoolean(this.getString(R.string.flagName), false)

        fun updateFlagText( ){
            flagValue = sharedPref.getBoolean(this.getString(R.string.flagName), false)
            flagText.text = flagValue.toString()
        }

        updateFlagText()

        fun updatePref( value: Boolean){
            sharedPref.edit().putBoolean(this.getString(R.string.flagName), value).apply()
            updateFlagText()
        }

        /**
         * Changing Pref Flag from True to False everytime the button is clicked
         */
        val btnFlag = findViewById<Button>(R.id.flagBtn)

        btnFlag.setOnClickListener {
            updatePref( !flagValue )
            Toast.makeText(this@MainActivity, "Preference Flag Flipped to " + (flagValue).toString() + "!", Toast.LENGTH_SHORT).show()

        }
    }
}
