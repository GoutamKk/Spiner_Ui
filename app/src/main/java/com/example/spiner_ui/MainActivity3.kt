package com.example.spiner_ui

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spiner_ui.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    private var arr = arrayListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arr)
        binding.listId.adapter = arrayAdapter
        binding.listId.setOnItemClickListener { parent, view, position, id ->
            pos = position
        }
        binding.floatBtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_layout2)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
            val add = dialog.findViewById<Button>(R.id.add)
            val update = dialog.findViewById<Button>(R.id.update)
            val delete = dialog.findViewById<Button>(R.id.delete)
            val cancle = dialog.findViewById<Button>(R.id.cancle)
            val edit = dialog.findViewById<EditText>(R.id.edit_text)

            if(pos >= 0){
                edit.setText(binding.listId.getItemAtPosition(pos).toString())
            }
            add.setOnClickListener {
                if(edit.text.isNotEmpty()){
                    arr.add(edit.text.toString())
                    edit.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                    pos++
                }
                else{
                    edit.error = "Enter Something"
                }
            }
            update.setOnClickListener {
                if(edit.text.isNotEmpty() && pos >= 0){
                    arr.removeAt(pos)
                    arr.add(pos,edit.text.toString())
                    edit.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                }
                else if(pos < 0){
                    edit.error = "List is empty"
                }
                else {
                    edit.error = "Enter Something"
                }
            }
            delete.setOnClickListener {
                if(edit.text.isNotEmpty()){
                    arr.removeAt(pos)
                    edit.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                }
                else{
                    edit.error = "Enter Something"
                }
            }
            cancle.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}