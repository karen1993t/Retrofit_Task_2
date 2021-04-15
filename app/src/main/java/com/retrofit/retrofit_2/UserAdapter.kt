package com.retrofit.retrofit_2

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URI
import java.net.URL

class UserAdapter(val context: Context, var data: MutableList<UsersModelList.UserSingleData>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.model_users_recycler, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.userId.text = data[position].id.toString()
        holder.userEmail.text = data[position].email
        holder.userFirstName.text = data[position].firstName
        holder.userLastName.text = data[position].lastName
        GlobalScope.launch(Dispatchers.IO) {
            val uri = URL(data[position].avatar)
            val input: InputStream = URL("$uri").openStream()
            val bmp = BitmapFactory.decodeStream(input)

            withContext(Dispatchers.Main) {
                holder.userAvatar.setImageBitmap(bmp)
            }
        }
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userId: TextView
        val userEmail: TextView
        val userFirstName: TextView
        val userLastName: TextView
        val userAvatar: ImageView


        init {
            userId = view.findViewById(R.id.tv_user_id)
            userEmail = view.findViewById(R.id.tv_user_email)
            userFirstName = view.findViewById(R.id.tv_user_first_name)
            userLastName = view.findViewById(R.id.tv_user_last_name)
            userAvatar = view.findViewById(R.id.img_avatar)

        }
    }
}
