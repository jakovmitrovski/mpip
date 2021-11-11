package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.content.ComponentName

import android.content.IntentFilter

import android.content.Intent

import android.content.pm.PackageManager
import android.widget.ArrayAdapter


class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        val data = initData()

        val listView = findViewById<ListView>(R.id.myListView)

        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
    }

    private fun initData(): MutableList<String>{
        val pm: PackageManager = applicationContext.packageManager

        val filter = IntentFilter(Intent.ACTION_MAIN)
        filter.addCategory(Intent.CATEGORY_LAUNCHER)

        val outFilters: MutableList<IntentFilter> = ArrayList()
        outFilters.add(filter)

        val outActivities: List<ComponentName> = ArrayList()
        pm.getPreferredActivities(outFilters, outActivities, null)

        val ret = mutableListOf<String>()

        outActivities.forEach { ret.add(it.className) }

        return ret
    }
}