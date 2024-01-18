package com.waffle.charismatic.domain.repository

import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun postLogin(
        loginRequest: LoginRequest
    ) : Flow<Result<LoginResponse>>
}
