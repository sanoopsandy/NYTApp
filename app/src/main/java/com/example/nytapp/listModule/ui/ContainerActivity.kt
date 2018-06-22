package com.example.nytapp.listModule.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.nytapp.R
import com.example.nytapp.utils.addFragment
import com.example.nytapp.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_container.*


class ContainerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setSupportActionBar(toolbar)
        addFragment(TopStoriesFragment.newInstance(1), R.id.container)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_container, menu)

        val item = menu.findItem(R.id.spinner)
        val spinner = item.getActionView() as Spinner

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.types, R.layout.spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                replaceFragment(TopStoriesFragment.newInstance(position + 1), R.id.container)
            }

        })
        return true
    }
}
