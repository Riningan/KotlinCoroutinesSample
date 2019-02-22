package com.riningan.dota2heroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riningan.dota2heroes.R
import toothpick.Toothpick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScope(this.javaClass.canonicalName)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        Toothpick.closeScope(this.javaClass.canonicalName)
        super.onDestroy()
    }
}
