package byun91.delivery.data.repository

import byun91.delivery.data.entity.LocationLatLngEntity
import byun91.delivery.data.response.address.AddressInfo

interface MapRepository {

    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo?


}