package com.example.cinedrivein.di

import com.example.cinedrivein.common.constants.Constants
import com.example.cinedrivein.data.remote.service.ancine.AncineApi
import com.example.cinedrivein.data.remote.service.ancine.AncineInterceptor
import com.example.cinedrivein.data.repository.AncineRepositoryImpl
import com.example.cinedrivein.domain.repository.AncineRepository
import com.example.cinedrivein.domain.usecase.LoginUseCase
import com.example.cinedrivein.domain.usecase.SendAncineReportUseCase
import com.example.cinedrivein.presentation.feature.ancine.create.viewmodel.CreateAncineReportViewModel
import com.example.cinedrivein.presentation.feature.login.viewmodel.LoginViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
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

    single {
        SendAncineReportUseCase(repository = get())
    }

    single {
        LoginUseCase()
    }

    viewModel {
        CreateAncineReportViewModel(sendAncineReportUseCase = get())
    }

    viewModel{
        LoginViewModel(loginUseCase = get())
    }
}