package com.example.mvpexample.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpexample.R
import com.example.mvpexample.data.UserResponse
import com.example.mvpexample.io.UserApi
import com.example.mvpexample.io.UserApiImp
import com.example.mvpexample.model.LoginRepositoryImp
import com.example.mvpexample.presenter.LoginPresenter
import com.example.mvpexample.presenter.LoginPresenterImp

class LoginActivity : AppCompatActivity(), LoginActivityView {

    private lateinit var etUser: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPresenter()
        setupUI()
    }

    private fun setupUI() {
        etUser = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)

        btnLogin.setOnClickListener {
            presenter.doLogin(
                etUser.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun createPresenter() {
        presenter = LoginPresenterImp(
            this,
            LoginRepositoryImp(UserApiImp())
        )
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun enableLoginButton() {
        btnLogin.isEnabled = true
    }

    override fun disableLoginButton() {
        btnLogin.isEnabled = false
    }

    override fun goToNextScreen(user: UserResponse) {
        //Mostrar siguiente pantalla
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}