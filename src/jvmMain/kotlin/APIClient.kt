import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer


val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }

    expectSuccess = false


}