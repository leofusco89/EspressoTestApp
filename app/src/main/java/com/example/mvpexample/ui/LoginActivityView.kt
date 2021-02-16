package com.example.mvpexample.ui

import com.example.mvpexample.data.UserResponse

interface LoginActivityView {
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
    fun enableLoginButton()
    fun disableLoginButton()
    fun goToNextScreen(user: UserResponse)
}