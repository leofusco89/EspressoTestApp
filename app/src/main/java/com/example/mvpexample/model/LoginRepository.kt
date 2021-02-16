package com.example.mvpexample.model

import com.example.mvpexample.data.User
import com.example.mvpexample.data.UserResponse

interface LoginRepository {
    fun login(user: User,
              responseSuccess: (UserResponse) -> Unit, responseError: (String) -> Unit)
}