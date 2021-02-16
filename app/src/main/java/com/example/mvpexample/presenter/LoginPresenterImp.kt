package com.example.mvpexample.presenter

import com.example.mvpexample.data.User
import com.example.mvpexample.model.LoginRepository
import com.example.mvpexample.ui.LoginActivityView

class LoginPresenterImp(
    private val view: LoginActivityView,
    private val model: LoginRepository
) : LoginPresenter {

    override fun doLogin(email: String, password: String) {
        view.showLoading()
        view.disableLoginButton()
        val user = User(email, password)
        if (dataIsCorrect(user)) {
            model.login(user,
                {
                    view.hideLoading()
                    view.goToNextScreen(it)
                },
                {
                    onDoLoginError(it)
                }
            )
        } else {
            onDoLoginError("Información inválida")
        }
    }

    private fun onDoLoginError(errorMessage: String) {
        view.showError(errorMessage)
        view.enableLoginButton()
        view.hideLoading()
    }

    fun dataIsCorrect(user: User): Boolean {
        return user.email.trim().length >= 2 &&
                user.password.trim().length >= 2
    }

}