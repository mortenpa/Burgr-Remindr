import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*


suspend fun addBurgerListItem(burgerListItem: LocalBurgerItem) {
    jsonClient.post<Unit>("http://localhost:9090/" + LocalBurgerItem.path) {
        contentType(ContentType.Application.Json)
        body = burgerListItem
    }
}

suspend fun deleteBurgerListItem(burgerListItem: LocalBurgerItem) {
    jsonClient.delete<Unit>("http://localhost:9090/" + LocalBurgerItem.path + "/${burgerListItem.id}")
}

