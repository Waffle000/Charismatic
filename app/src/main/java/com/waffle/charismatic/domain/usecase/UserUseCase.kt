package com.waffle.charismatic.domain.usecase

import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.response.LoginResponse
import com.waffle.charismatic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(private val userRepository: UserRepository) {
    fun postLogin(
        loginRequest: LoginRequest
    ) : Flow<Result<LoginResponse>> {
        return userRepository.postLogin(loginRequest)
    }
}