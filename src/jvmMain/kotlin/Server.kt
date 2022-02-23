import com.mongodb.ConnectionString
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo



//TODO tests and error handling
fun main() {

    val serverPort: Int = System.getenv("PORT").toInt()
    val connectionString: ConnectionString? = System.getenv("MONGODB_URI")?.let {
        ConnectionString("$it?retryWrites=false")
    }
    val foursquareAPIToken = System.getenv("FOURSQUARE_API_TOKEN")

    val client = if (connectionString != null) KMongo.createClient(connectionString).coroutine else KMongo.createClient().coroutine
    val database = client.getDatabase(connectionString?.database ?: "burgerList")
    val collection = database.getCollection<LocalBurgerItem>()

    val burgerHelper = BurgerHelper(foursquareAPIToken)



    embeddedServer(Netty, serverPort){
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            get("/") {

                burgerHelper.checkForNewBurgers()

                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }

            //TODO add authentication
            route(LocalBurgerItem.path) {

                get {
                    call.respond(collection.find().toList())
                }
                post {
                    collection.insertOne(call.receive<LocalBurgerItem>())
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    collection.deleteOne(LocalBurgerItem::id eq id)
                    call.respond(HttpStatusCode.OK)
                }
            }

        }
    }.start(wait = true)

}