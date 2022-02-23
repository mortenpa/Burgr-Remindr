import kotlinx.serialization.Serializable

@Serializable
data class FoursquarePlaceCircle(val center: FoursquarePlaceCenter,
                                 val radius: Int)