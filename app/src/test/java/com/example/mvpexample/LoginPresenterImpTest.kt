package com.example.mvpexample

import com.example.mvpexample.data.User
import com.example.mvpexample.model.LoginRepository
import com.example.mvpexample.presenter.LoginPresenterImp
import com.example.mvpexample.ui.LoginActivityView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginPresenterImpTest {

    @Mock
    private lateinit var view: LoginActivityView

    @Mock
    private lateinit var model: LoginRepository

    private lateinit var presenter: LoginPresenterImp


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = LoginPresenterImp(view, model)
    }

    @Test
    fun userIsValid_When_Email_Length_AND_Password_Are_Higher_than_1() {
        val user = User("email", "password")

        val userIsValid = presenter.dataIsCorrect(user)

        Assert.assertTrue(userIsValid)
    }

    @Test
    fun userIsInvalid_When_Email_Is_Higher_than_1_AND_Password_Is_Lower_than_1() {
        val user = User("email", "p")

        val userIsValid = presenter.dataIsCorrect(user)

        Assert.assertFalse(userIsValid)
    }

    @Test
    fun userIsInvalid_When_Email_Is_Lower_than_1_AND_Password_Is_Higher_than_1() {
        val user = User("e", "password")

        val userIsValid = presenter.dataIsCorrect(user)

        Assert.assertFalse(userIsValid)
    }

    @Test
    fun userIsInvalid_When_Email_Is_Lower_than_1_AND_Password_Is_Lower_than_1() {
        val user = User("e", "p")

        val userIsValid = presenter.dataIsCorrect(user)

        Assert.assertFalse(userIsValid)
    }

    @Test
    fun showLoadingIsCalled_when_doLogin() {
        presenter.doLogin("","")

        Mockito.verify(view).showLoading()
    }

    @Test
    fun disableLoginButtonIsCalled_when_doLogin() {
        presenter.doLogin("","")

        Mockito.verify(view).disableLoginButton()
    }


}