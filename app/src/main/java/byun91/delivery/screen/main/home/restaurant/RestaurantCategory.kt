package byun91.delivery.screen.main.home.restaurant

import androidx.annotation.StringRes
import byun91.delivery.R

enum class RestaurantCategory (
    @StringRes val categoryNameId:Int,
    @StringRes val categoryTypeId: Int){
    ALL(R.string.all, R.string.all_type)
}