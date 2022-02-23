import kotlinx.serialization.json.Json
import org.litote.kmongo.formatJson
import java.time.Duration
import java.time.Instant

class BurgerHelper constructor(private val foursquareAPIToken: String){
    private var burgersPreviouslyUpdated = Instant.now()
    private val burgerUpdateFrequencyInSeconds = 30
    private val foursquarePlaceRequest = 10

    private fun transformFoursquarePhotoListToURLList(foursquarePhotos: List<FoursquarePhoto>): MutableList<String> {
        val photoURLs = mutableListOf<String>()
        for (foursquarePhoto in foursquarePhotos){
            photoURLs.add(foursquarePhoto.fullURL)
        }
        return photoURLs
    }

    private fun findDateFromPhotoList(fourSquarePhotos: List<FoursquarePhoto>, photoURL: String): String{
        for (photo in fourSquarePhotos){
            if(photo.fullURL == photoURL)
                return(photo.created_at)
        }
        return("")
    }

    private suspend fun processNewBurgers(){

        //TODO support for more than 50 places (needs multiple API calls)
        val foursquarePlaces = getFoursquarePlaces(foursquareAPIToken, foursquarePlaceRequest).results

        for (place in foursquarePlaces){

            val fourSquarePhotos = getFourSquarePhotos(foursquareAPIToken, place.fsq_id)
            val possibleBurgerPhotos = transformFoursquarePhotoListToURLList(fourSquarePhotos)

            //TODO fix this hack
            val burgerPhotoURLSplit = burgerInlistOfPhotos(possibleBurgerPhotos).split("urlWithBurger\":\"")
            if (burgerPhotoURLSplit.size > 1){
                val burgerPhotoURL = burgerPhotoURLSplit[1].replace("\"}","")
                val localBurger: LocalBurgerItem = LocalBurgerItem(burgerPhotoURL.toString(), place.name, findDateFromPhotoList(fourSquarePhotos, burgerPhotoURL))
                deleteBurgerListItem(localBurger)
                addBurgerListItem(localBurger)
            }
        }


    }

    //update burgers every n minutes
    suspend fun checkForNewBurgers(){
        val burgerLastUpdateDuration = Duration.between(burgersPreviouslyUpdated, Instant.now()).seconds
        if (burgerLastUpdateDuration > burgerUpdateFrequencyInSeconds){
            burgersPreviouslyUpdated = Instant.now()
            processNewBurgers()
        }
    }

}