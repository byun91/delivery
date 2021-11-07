package byun91.delivery.di

import byun91.delivery.data.repository.DefaultMapRepository
import byun91.delivery.data.repository.DefaultRestaurantRepository
import byun91.delivery.data.repository.MapRepository
import byun91.delivery.data.repository.RestaurantRepository
import byun91.delivery.screen.main.home.HomeViewModel
import byun91.delivery.screen.main.home.restaurant.RestaurantCategory
import byun91.delivery.screen.main.home.restaurant.RestaurantListViewModel
import byun91.delivery.screen.main.my.MyViewModel
import byun91.delivery.screen.mylocation.MyLocationViewModel
import byun91.delivery.util.provider.DefaultResourceProvider
import byun91.delivery.util.provider.ResourceProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{HomeViewModel(get())}
    viewModel{ MyViewModel() }
    viewModel { MyLocationViewModel() }

    viewModel { (restaurantCategory: RestaurantCategory) ->
        RestaurantListViewModel(restaurantCategory, restaurantRepository = get())
    }

    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get()) }

    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }
    single { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get()) }

    single<ResourceProvider> {DefaultResourceProvider(androidApplication())}
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}