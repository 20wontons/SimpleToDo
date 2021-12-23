package com.example.simpletodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

class EditActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val taskText = getIntent().getStringExtra("taskText")
        val position = getIntent().getIntExtra("position", 0)

        val inputTextField = findViewById<EditText>(R.id.editTaskField)

        inputTextField.setText(taskText)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val data = Intent()

            data.putExtra("editedTask", inputTextField.text.toString())
            data.putExtra("position", position)
            data.putExtra("code", 200)

            setResult(RESULT_OK, data)
            finish()
        }

    }


}