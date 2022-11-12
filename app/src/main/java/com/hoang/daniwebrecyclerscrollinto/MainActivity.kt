package com.hoang.daniwebrecyclerscrollinto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver

private const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Handle data in ViewModel in real code
        val data = ('a'..'z')
            .map { "$it" }
            .toMutableList()

        val myAdapter = MyAdapter(data)
        recyclerView.adapter = myAdapter

        // Adds observer to scroll into position
        val observer = object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recyclerView.smoothScrollToPosition(positionStart)
            }
        }
        myAdapter.registerAdapterDataObserver(observer)

        // Add first
        var counter = 0
        val addFirstButton = findViewById<Button>(R.id.button_addFirst)
        addFirstButton.setOnClickListener {
            data.add(0, counter++.toString())
            myAdapter.notifyItemInserted(0)
        }

        // Add Last
        val addLastButton = findViewById<Button>(R.id.button_addLast)
        addLastButton.setOnClickListener {
            data.add(counter++.toString())
            myAdapter.notifyItemInserted(data.lastIndex)
        }
    }
}