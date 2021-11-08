package byun91.delivery.screen.mylocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import byun91.delivery.data.entity.MapSearchInfoEntity
import byun91.delivery.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyLocationViewModel(
   private val mapSearchInfoEntity: MapSearchInfoEntity
) : BaseViewModel() {

   val myLocationStateLiveData = MutableLiveData<MyLocationState>(MyLocationState.Uninitialized)

   override fun fetchData(): Job = viewModelScope.launch {
         myLocationStateLiveData.value = MyLocationState.Loading
         myLocationStateLiveData.value = MyLocationState.Success(
            mapSearchInfoEntity
         )
      }
}