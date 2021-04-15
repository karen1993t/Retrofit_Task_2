package com.retrofit.retrofit_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var list: MutableList<UsersModelList.UserSingleData>? = null
        var userList = mutableListOf<UsersModelList.UserSingleData>()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_users)
        val btnClick = findViewById<Button>(R.id.btn_get)





        GlobalScope.launch(Dispatchers.IO) {
            val call = UserRetrofitService.retrofit.create(UsersApi::class.java)
                .getUsersList(2)
            val parser = call.execute()

            if (parser.isSuccessful) {
                list = parser.body()?.data
                withContext(Dispatchers.Main) {
                    var newList = list
                    if (newList != null) {
                        userList.addAll(newList)
                    }
                }
            }
        }

            btnClick.setOnClickListener() {
                if (userList.isNotEmpty()) {
                val userAdapter = UserAdapter(this, userList)
                recyclerView.adapter = userAdapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "Loaded.......", Toast.LENGTH_SHORT).show()
            }
        }
    }
}