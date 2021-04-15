package com.retrofit.retrofit_2

import com.google.gson.annotations.SerializedName

class UsersModelList {
     val data: UsersModelList? = null

    data class UserSingleData(
        var id : Int?,
        var email:String?,
        @SerializedName("first_name")
        var firstName:String?,
        @SerializedName("last_name")
        var lastName:String?,
        var avatar:String?
    ){

    }

    data class  UsersListModel(
        var data:MutableList<UserSingleData>?
    ){

    }
}