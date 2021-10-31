package byun91.delivery.screen.main.home

import androidx.lifecycle.MutableLiveData
import byun91.delivery.screen.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)
}