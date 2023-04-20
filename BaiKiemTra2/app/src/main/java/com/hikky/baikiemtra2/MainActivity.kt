package com.hikky.baikiemtra2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hikky.baikiemtra2.adapter.ViewAdapter
import com.hikky.baikiemtra2.model.ProductModel
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var productRecyclerView : RecyclerView
    private lateinit var productAdapter: ViewAdapter
    private lateinit var productList : ArrayList<ProductModel>
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDbRef = FirebaseDatabase.getInstance().reference
        productRecyclerView = findViewById(R.id.listProduct)
        productList = ArrayList()
        productAdapter = ViewAdapter(this,productList)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = productAdapter

        mDbRef.child("SanPham")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    productList.clear()
                    for(i in snapshot.children) {
                        val product = i.getValue(ProductModel::class.java)

                        productList.add(product!!)
                    }
                    productAdapter.notifyDataSetChanged()

                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuAdd) {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
