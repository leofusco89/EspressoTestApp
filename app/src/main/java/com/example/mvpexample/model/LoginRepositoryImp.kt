package com.example.mvpexample.model

import com.example.mvpexample.data.User
import com.example.mvpexample.data.UserResponse
import com.example.mvpexample.io.UserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginRepositoryImp(
    private val userApi: UserApi
) : LoginRepository {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun login(
        user: User,
        responseSuccess: (UserResponse) -> Unit,
        responseError: (String) -> Unit
    ) {
        compositeDisposable.add(
            userApi
                .login(user)
                .delay(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { responseSuccess.invoke(it) },
                    { responseError.invoke(it.message!!) }
                )
        )
    }
}