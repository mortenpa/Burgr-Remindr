import kotlinx.serialization.Serializable


@Serializable
data class FoursquarePhoto(val id: String,
                           val created_at: String,
                           val prefix: String,
                           val suffix: String,
                           val width: Int,
                           val height: Int,
                           val classifications: List<String>) {

    // https://developer.foursquare.com/reference/places-photos-guide#assembling-a-photo-url
    val fullURL: String = prefix + "original" + suffix

}