package com.example.writeimgtodb

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.writeimgtodb.Db.MyDbHelper
import com.example.writeimgtodb.adapters.MyRecyAdapter
import com.example.writeimgtodb.databinding.ActivityMainBinding
import com.example.writeimgtodb.models.RecyItemModel
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: MyDbHelper
    private var selectedImageUri: Uri? = null
    private lateinit var myRecyAdapter: MyRecyAdapter

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            selectedImageUri = it
            binding.tvImg.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = MyDbHelper(this)
        loadRecycler()

        binding.tvImg.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.names.text.toString()
            if (name.isNotEmpty() && selectedImageUri != null) {
                val path = saveImageToInternalStorage(selectedImageUri!!)
                val model = RecyItemModel(0, name, path)
                dbHelper.addItem(model)
                binding.names.text.clear()
                binding.tvImg.setImageResource(R.drawable.ic_launcher_foreground)
                selectedImageUri = null
                loadRecycler()
                Toast.makeText(this, "Ro'yxatga bitta eklement qo'shildi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadRecycler() {
        val list = dbHelper.getItem()
        myRecyAdapter = MyRecyAdapter(list)
        binding.recy.layoutManager = LinearLayoutManager(this)
        binding.recy.adapter = myRecyAdapter
    }

    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream = contentResolver.openInputStream(uri)
        val fileName = "img_${System.currentTimeMillis()}.jpg"
        val file = File(filesDir, fileName)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file.absolutePath
    }
}
