package com.example.mvpexample

import com.example.mvpexample.data.User
import com.example.mvpexample.data.UserResponse
import com.example.mvpexample.io.UserApi
import com.example.mvpexample.model.LoginRepositoryImp
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class LoginRepositoryImpTest {

    private lateinit var loginRepositoryImp: LoginRepositoryImp
    private var user: User = User("nicodiaz@gmail.com", "123456")

    @Mock
    lateinit var userApi: UserApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        loginRepositoryImp = LoginRepositoryImp(userApi)
    }

    @Test
    fun verify_disposable_is_called_when_login_is_called() {
        Mockito.`when`(userApi.login(user))
            .thenReturn(Single.just(UserResponse("Nicolas", "Diaz")))

        loginRepositoryImp.login(
            user,
            {},
            {}
        )

        Mockito.verify(userApi).login(user)
    }
}