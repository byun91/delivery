package byun91.delivery.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // activity result -> getParcelableExtra
data class MapSearchInfoEntity(
    val fullAddress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
): Parcelable
