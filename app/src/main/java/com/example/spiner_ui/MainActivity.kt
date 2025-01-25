package com.example.spiner_ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spiner_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var array = ArrayList<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    var pos = -1
    var data = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,array)
        binding.spinnerid.adapter = arrayAdapter
        binding.spinnerid.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos= position
                data =binding.spinnerid.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.uiId.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        binding.changeid.setOnClickListener {
           val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_layout)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()

            val add = dialog.findViewById<Button>(R.id.addId)
            val update = dialog.findViewById<Button>(R.id.updateId)
            val delete = dialog.findViewById<Button>(R.id.deleteId)
            val cancl = dialog.findViewById<Button>(R.id.cancleId)
            val edit = dialog.findViewById<EditText>(R.id.edit_text_id)

            if (pos != -1){
                edit.setText(data)
            }

            add.setOnClickListener {
                if(edit.text.isNotEmpty()){
                    array.add(edit.text.toString())
                    arrayAdapter.notifyDataSetChanged()
                    edit.text.clear()
                }
                else{
                    Toast.makeText(this,"Enter data in edit text",Toast.LENGTH_SHORT).show()
                }
            }
            update.setOnClickListener {
                if(edit.text.isNotEmpty() && array.size>0 ){
                    array[pos] = edit.text.toString()
                    arrayAdapter.notifyDataSetChanged()
                    edit.text.clear()
                }
                else{
                    Toast.makeText(this,"Enter data in edit text",Toast.LENGTH_SHORT).show()
                }
            }
            delete.setOnClickListener {
                if(edit.text.isNotEmpty() ){
                    array.remove(binding.spinnerid.selectedItem.toString())
                    arrayAdapter.notifyDataSetChanged()
                    edit.text.clear()
                }
                else{
                    Toast.makeText(this,"Enter data in edit text",Toast.LENGTH_SHORT).show()
                }
            }
            cancl.setOnClickListener {
                dialog.dismiss()
            }
        }
        binding.listButon.setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }

    }
}