package com.example.writeimgtodb.Db

import androidx.activity.result.contract.ActivityResultContracts
import com.example.writeimgtodb.adapters.MyRecyAdapter
import com.example.writeimgtodb.models.RecyItemModel
import java.io.File
import java.io.FileOutputStream

interface DbHelperInterface {
    fun addItem(recyItemModel: RecyItemModel)
    fun getItem(): List<RecyItemModel>
//    binding.btn.setOnClickListener {
//        img.launch("image/*")
//    }
//
//
//
//val img = registerForActivityResult(ActivityResultContracts.GetContent()) {
//    it ?: return@registerForActivityResult
//    binding.tevImg.setImageURI(it)
//
//    val inputStream = contentResolver?.openInputStream(it)
//    val file = File(filesDir, "First.jpg")
//    val fileOutputStream = FileOutputStream(file)
//    inputStream?.copyTo(fileOutputStream)
//    inputStream?.close()
//    fileOutputStream?.close()
//
//    binding.btn.text = file.absolutePath
//}
}