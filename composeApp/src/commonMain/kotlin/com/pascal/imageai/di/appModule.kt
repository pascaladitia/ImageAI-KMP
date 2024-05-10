package com.pascal.imageai.di

import com.pascal.imageai.DriverFactory
import com.pascal.imageai.data.local.LocalRepository
import com.pascal.imageai.data.repository.Repository
import com.pascal.imageai.db.MyDatabase
import com.pascal.imageai.presentation.screen.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single { MyDatabase(DriverFactory().createDriver()) }
    single{ Repository() }
    single{ LocalRepository(get()) }
    singleOf(::HomeViewModel)
}