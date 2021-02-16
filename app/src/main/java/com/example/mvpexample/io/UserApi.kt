package com.example.mvpexample.io

import com.example.mvpexample.data.User
import com.example.mvpexample.data.UserResponse
import io.reactivex.Single

class UserApiImp : UserApi {

    override fun login(user: User): Single<UserResponse> =
        Single.just(UserResponse("Jose", "Argento"))

    override fun loginError(): Single<UserResponse> = Single.error(Throwable("Datos incorrectos"))

}

interface UserApi {

    fun login(user: User): Single<UserResponse>

    fun loginError(): Single<UserResponse>

}