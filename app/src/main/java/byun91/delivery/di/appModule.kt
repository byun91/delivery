package byun91.delivery.di

import byun91.delivery.screen.main.home.HomeViewModel
import byun91.delivery.screen.main.my.MyViewModel
import byun91.delivery.util.provider.DefaultResourceProvider
import byun91.delivery.util.provider.ResourceProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{HomeViewModel()}
    viewModel{ MyViewModel() }

    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }
    single { provideRetrofit(get(), get()) }


    single<ResourceProvider> {DefaultResourceProvider(androidApplication())}
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}