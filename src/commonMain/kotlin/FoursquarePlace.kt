import kotlinx.serialization.Serializable



@Serializable
data class FoursquarePlace(val fsq_id: String,
                           val name: String)