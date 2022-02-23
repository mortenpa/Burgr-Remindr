import io.ktor.client.request.*
import io.ktor.http.*


suspend fun getFoursquarePlaces(accessToken: String, placeCount: Int): FoursquarePlaceResponse {

    return jsonClient.get("https://api.foursquare.com/v3/places/search?query=burger&ll=58.378025%2C26.728493&fields=fsq_id%2Cname&limit=$placeCount") {
        headers {
            append(HttpHeaders.Accept, "application/json")
            append(HttpHeaders.Authorization, accessToken)
        }

    }
}

suspend fun getFourSquarePhotos(accessToken: String, fsq_id: String): List<FoursquarePhoto>{
    return jsonClient.get("https://api.foursquare.com/v3/places/$fsq_id/photos?limit=50&sort=NEWEST&classifications=food"){
        headers {
            append(HttpHeaders.Accept, "application/json")
            append(HttpHeaders.Authorization, accessToken)
        }
    }
}