A project directed at finding burgers at local eateries. Utilizing Kotlin and MongoDB alongside React!

Environment variables required to make the project work:








| Variable                        | Type | Explanation  |
| ------------------------------- |:-----: | --------------------------------------------------------- |
| FOURSQUARE_API_TOKEN            | String | Foursquare API key                                        |
| PORT                            | Int    | Server port                                               |
| ORG_GRADLE_PROJECT_isProduction | Bool   | true for production                                       |
| baseURL                         | String | Webapp deployment base URL                                |
| BURGER_UPDATE_FREQUENCY_SECONDS | Int    | How often burgers are updated (in seconds)                |
| BURGER_REQUEST_COUNT_FOURSQUARE | Int    | How many places to query at once from Foursquare (max 50) |
