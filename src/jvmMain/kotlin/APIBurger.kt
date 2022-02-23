import io.ktor.client.request.*
import io.ktor.http.*

val baseURL: String = System.getenv("baseURL");

suspend fun addBurgerListItem(burgerListItem: LocalBurgerItem) {
    jsonClient.post<Unit>(baseURL + LocalBurgerItem.path) {
        contentType(ContentType.Application.Json)
        body = burgerListItem
    }
}

suspend fun deleteBurgerListItem(burgerListItem: LocalBurgerItem) {
    jsonClient.delete<Unit>(baseURL + LocalBurgerItem.path + "/${burgerListItem.id}")
}

