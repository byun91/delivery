package byun91.delivery.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import byun91.delivery.R
import byun91.delivery.data.entity.LocationLatLngEntity
import byun91.delivery.data.entity.MapSearchInfoEntity
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
            homeStateLiveData.value = HomeState.Success(
                MapSearchInfoEntity(
                    fullAddress = info.fullAddress ?: "주소 정보 없음",
                    name = info.buildingName ?: "빌딩정보 없음",
                    locationLatLng = locationLatLngEntity
                )
            )
        }?: kotlin.run {
            homeStateLiveData.value = HomeState.Error(
                R.string.can_not_load_address_info
            )
        }
    }

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)
}