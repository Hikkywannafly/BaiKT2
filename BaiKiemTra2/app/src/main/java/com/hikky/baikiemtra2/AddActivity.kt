package com.hikky.baikiemtra2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.hikky.baikiemtra2.model.ProductModel
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.math.log


class AddActivity: AppCompatActivity() {

    var btnCapture: ImageButton? = null
    var btnChoose: ImageButton? = null
    var imgPicture: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        addControls()
        addEvents()

    }
    fun addControls() {
        btnCapture = findViewById(R.id.btnCapture)
        btnChoose = findViewById(R.id.btnChoose)
        imgPicture = findViewById(R.id.imgPicture)
    }

    fun addEvents() {
        btnCapture?.setOnClickListener(View.OnClickListener { capturePicture() })
        btnChoose?.setOnClickListener(View.OnClickListener { choosePicture() })
    }

    private fun choosePicture() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhoto, 200)
    }


    private fun capturePicture() {
        val cInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cInt, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                val thumbnail = data!!.extras!!.get("data") as Bitmap
                imgPicture!!.setImageBitmap(thumbnail)
            } else if (requestCode == 200) {
                val selectedImage = data!!.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = contentResolver.query(
                    selectedImage!!,
                    filePathColumn, null, null, null
                )
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val picturePath = cursor.getString(columnIndex)
                cursor.close()
                imgPicture!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
            }
        }
    }
    fun handleAddData (View: View){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("SanPham")
        val tenSp = findViewById<EditText>(R.id.edtTen)
        val giaSp = findViewById<EditText>(R.id.edtGia)
        val loaiSp = findViewById<EditText>(R.id.edtLoai)
        val uid = UUID.randomUUID().toString()
        val bitmap = (imgPicture?.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val image = Base64.getEncoder().encodeToString(data)
        val product = ProductModel(uid, tenSp.text.toString(), giaSp.text.toString(), loaiSp.text.toString(), image)
        myRef.child(uid).setValue(product)

        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
            finish();
}

}
