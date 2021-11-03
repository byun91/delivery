package byun91.delivery.screen.main.home

import androidx.annotation.StringRes
import byun91.delivery.data.entity.MapSearchInfoEntity

sealed class HomeState {
    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
       val mapSearchInfo: MapSearchInfoEntity
    ): HomeState()

    data class Error(
        @StringRes val messageId : Int
    ) : HomeState()
}
