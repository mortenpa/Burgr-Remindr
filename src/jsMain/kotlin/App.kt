import react.*
import kotlinx.coroutines.*
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul


private val scope = MainScope()

val App = FC<Props> {
    var burgerList by useState(emptyList<LocalBurgerItem>())

    useEffectOnce {
        scope.launch {
            burgerList = getBurgerList()
        }
    }

    div {
        className="wrapper"
        h1 {
            +"Newest burgers!"
            className="h1"
        }
        ul {
            burgerList.sortedByDescending(LocalBurgerItem::pictureDate).forEach { item ->
                li {
                    //TODO DATE type and current date
                    className = (if(item.pictureDate > "2022-01-01")  "card card--new" else "card")
                    a {
                        href="${item.pictureURL}"
                        className="card__link"
                        img {
                            src=item.pictureURL
                            className = "card__image"
                        }
                        h3 {
                            + "${item.location}"
                            className="h3"
                        }
                        h4 {
                            + "${item.pictureDate.substring(0,10)}"
                            className="h4"
                        }
                        key = item.toString()
                    }
                    // +"[${item.pictureDate}] ${item.location}: ${item.pictureURL}"
                }

            }

        }
    }
}