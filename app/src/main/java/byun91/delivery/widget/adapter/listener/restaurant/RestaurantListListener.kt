package byun91.delivery.widget.adapter.listener.restaurant

import byun91.delivery.model.restaurant.RestaurantModel
import byun91.delivery.widget.adapter.listener.AdapterListener

interface RestaurantListListener : AdapterListener{

    fun onClickItem(model: RestaurantModel)
}