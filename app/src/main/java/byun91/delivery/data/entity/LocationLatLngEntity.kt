package byun91.delivery.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationLatLngEntity(
    val latitude: Double,
    val longitude: Double,
    val id: Long = -1, // 사용하지않는 값이라 인자에 맨 마지막 위치, 첫번째 위치에서는 에러발생
): Parcelable {

    override fun equals(other: Any?): Boolean {
        return if (other is LocationLatLngEntity) {
            (latitude == other.latitude
                    && longitude == other.longitude)
        } else {
            this === other
        }
    }

}

