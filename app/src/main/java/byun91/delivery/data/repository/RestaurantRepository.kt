package byun91.delivery.data.repository

import byun91.delivery.data.entity.RestaurantEntity
import byun91.delivery.screen.main.home.restaurant.RestaurantCategory

interface RestaurantRepository {
    suspend fun getList(
        restaurantCategory: RestaurantCategory
    ) : List<RestaurantEntity>
}