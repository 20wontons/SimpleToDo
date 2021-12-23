package com.example.simpletodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()

    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }
        }

//        fun launchEditView(position: Int) {
//            val i = Intent(this@MainActivity, EditActivity::class.java)
//            i.putExtra("taskText", listOfTasks.get(position))
//            startActivity(i)
//        }
//
//        val onClickListener = object : TaskItemAdapter.OnClickListener {
//            override fun onItemClicked(position: Int) {
//                launchEditView(position)
//            }
//        }

        loadItems()

//        findViewById<Button>(R.id.button).setOnClickListener {
//            Log.i("Caren", "User clicked on button")
//        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)//, onClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        findViewById<Button>(R.id.button).setOnClickListener {
            val userInputtedTask: String = inputTextField.text.toString()
            listOfTasks.add(userInputtedTask)
            adapter.notifyItemInserted(listOfTasks.size-1)
            inputTextField.setText("")
            saveItems()
        }
    }

    // Save the data that the user has inputted
    // By writing and reading from a file

    // Method to get the file we need
    fun getDataFile() : File {
        return File(filesDir, "data.txt")
    }

    // Method to load the items by reading every line in the data file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Method to save the items by writing them into our data file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}