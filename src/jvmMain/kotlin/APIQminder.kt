import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


//return an empty string if it doesn't recognize any burgers
suspend fun burgerInlistOfPhotos(urlsWithPossibleBurgers: List<String>): String{

    if (urlsWithPossibleBurgers.isEmpty())
        return ""

    return jsonClient.post("https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize"){
        headers{
            append(HttpHeaders.Accept, "application/json")
        }
        contentType(ContentType.Application.Json)
        body = QminderPhoto(urlsWithPossibleBurgers)
    }


}