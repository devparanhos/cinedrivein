package com.example.cinedrivein.di

import com.example.cinedrivein.common.constants.Constants
import com.example.cinedrivein.data.remote.service.ancine.AncineApi
import com.example.cinedrivein.data.remote.service.ancine.AncineInterceptor
import com.example.cinedrivein.data.remote.service.firestore.FirestoreService
import com.example.cinedrivein.data.repository.AncineRepositoryImpl
import com.example.cinedrivein.data.repository.DistributosRepositoryImpl
import com.example.cinedrivein.data.repository.UserRepositoryImpl
import com.example.cinedrivein.domain.repository.AncineRepository
import com.example.cinedrivein.domain.repository.DistributorsRepository
import com.example.cinedrivein.domain.repository.UserRepository
import com.example.cinedrivein.domain.usecase.*
import com.example.cinedrivein.domain.usecase.distributors.CreateDistributorUseCase
import com.example.cinedrivein.domain.usecase.distributors.DeleteDistributorUseCase
import com.example.cinedrivein.domain.usecase.distributors.GetDistributorsUseCase
import com.example.cinedrivein.domain.usecase.login.LoginUseCase
import com.example.cinedrivein.domain.usecase.login.LogoutUseCase
import com.example.cinedrivein.domain.usecase.login.RecoverPasswordUseCase
import com.example.cinedrivein.domain.usecase.user.CheckUserUseCase
import com.example.cinedrivein.domain.usecase.user.CreateUserUseCase
import com.example.cinedrivein.domain.usecase.user.GetUserUseCase
import com.example.cinedrivein.domain.usecase.user.RegisterUseCase
import com.example.cinedrivein.presentation.feature.ancine.create.viewmodel.CreateAncineReportViewModel
import com.example.cinedrivein.presentation.feature.distributors.create.viewmodel.CreateDistributorViewModel
import com.example.cinedrivein.presentation.feature.distributors.list.viewmodel.DistributorsViewModel
import com.example.cinedrivein.presentation.feature.home.viewmodel.HomeViewModel
import com.example.cinedrivein.presentation.feature.login.viewmodel.LoginViewModel
import com.example.cinedrivein.presentation.feature.menu.viewmodel.MenuViewModel
import com.example.cinedrivein.presentation.feature.register.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
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

    single {
        FirestoreService(firestore = db)
    }

    single<AncineRepository> {
        AncineRepositoryImpl(api = get())
    }

    single<UserRepository>{
        UserRepositoryImpl(auth = auth, firestoreService = get())
    }

    single<DistributorsRepository>{
        DistributosRepositoryImpl(firestoreService = get())
    }

    single {
        SendAncineReportUseCase(repository = get())
    }

    single {
        CheckUserUseCase(repository = get())
    }

    single {
        GetUserUseCase(repository = get())
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

    single {
        CreateUserUseCase(repository = get())
    }

    single {
        RecoverPasswordUseCase(repository = get())
    }

    single {
        GetDistributorsUseCase(repository = get())
    }

    single{
        DeleteDistributorUseCase(repository = get())
    }

    single{
        CreateDistributorUseCase(repository = get())
    }

    viewModel {
        CreateAncineReportViewModel(sendAncineReportUseCase = get())
    }

    viewModel{
        LoginViewModel(loginUseCase = get(), checkUserUseCase = get(), recoverPasswordUseCase = get())
    }

    viewModel{
        HomeViewModel(getUserUseCase = get())
    }

    viewModel{
        RegisterViewModel(registerUseCase = get(), createUserUseCase = get())
    }

    viewModel{
        MenuViewModel(logoutUseCase = get())
    }

    viewModel{
        DistributorsViewModel(getDistributorsUseCase = get(), deleteDistributorUseCase = get())
    }

    viewModel {
        CreateDistributorViewModel(createDistributorUseCase = get())
    }
}