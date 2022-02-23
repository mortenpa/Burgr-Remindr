import kotlinx.serialization.Serializable


@Serializable
data class LocalBurgerItem(val pictureURL: String,
                           val location: String,
                           //TODO serializable date value
                           val pictureDate: String) {
    val id: Int = location.hashCode()

    companion object {
        const val path = "/burgerList"
    }
}