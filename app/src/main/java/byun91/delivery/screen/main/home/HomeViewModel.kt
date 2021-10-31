package byun91.delivery.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import byun91.delivery.data.entity.LocationLatLngEntity
import byun91.delivery.data.repository.MapRepository
import byun91.delivery.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
   private val mapRepository: MapRepository
) : BaseViewModel() {
    fun loadReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity)
    = viewModelScope.launch {
        homeStateLiveData.value = HomeState.Loading
      val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)
        addressInfo?.let { info ->


        }?: kotlin.run {

        }
    }

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)
}