package com.example.cinedrivein.di

import com.example.cinedrivein.common.constants.Constants
import com.example.cinedrivein.data.remote.service.ancine.AncineApi
import com.example.cinedrivein.data.remote.service.ancine.AncineInterceptor
import com.example.cinedrivein.data.repository.AncineRepositoryImpl
import com.example.cinedrivein.data.repository.LoginRepositoryImpl
import com.example.cinedrivein.data.repository.RegisterRepositoryImpl
import com.example.cinedrivein.domain.repository.AncineRepository
import com.example.cinedrivein.domain.repository.LoginRepository
import com.example.cinedrivein.domain.repository.RegisterRepository
import com.example.cinedrivein.domain.usecase.*
import com.example.cinedrivein.presentation.feature.ancine.create.viewmodel.CreateAncineReportViewModel
import com.example.cinedrivein.presentation.feature.home.viewmodel.HomeViewModel
import com.example.cinedrivein.presentation.feature.login.viewmodel.LoginViewModel
import com.example.cinedrivein.presentation.feature.register.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val ancineClient = OkHttpClient.Builder().apply {
        addInterceptor(AncineInterceptor())
    }.build()

    single{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(ancineClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AncineApi::class.java)
    }

    single<AncineRepository> {
        AncineRepositoryImpl(api = get())
    }

    single<LoginRepository>{
        LoginRepositoryImpl(auth = auth)
    }

    single<RegisterRepository>{
        RegisterRepositoryImpl(auth = auth)
    }

    single {
        SendAncineReportUseCase(repository = get())
    }

    single {
        CheckUserUseCase(repository = get())
    }

    single {
        LoginUseCase(repository = get())
    }

    single {
        LogoutUseCase(repository = get())
    }

    single {
        RegisterUseCase(repository = get())
    }

    viewModel {
        CreateAncineReportViewModel(sendAncineReportUseCase = get())
    }

    viewModel{
        LoginViewModel(loginUseCase = get(), checkUserUseCase = get())
    }

    viewModel{
        HomeViewModel(logoutUseCase = get())
    }

    viewModel{
        RegisterViewModel(registerUseCase = get())
    }
}