A project directed at finding burgers at local eateries. Utilizing Kotlin and MongoDB alongside React!

Environment variables required to make the project work:


| Variable                        | Type   | Explanation                                               | Example                                          |
| ------------------------------- |:-----: | --------------------------------------------------------- | ------------------------------------------------ |
| FOURSQUARE_API_TOKEN            | String | Foursquare API key                                        | fsqaaaaabbbbbbbbbccccccccccdsdddddddeeeeeeeeeed= |
| PORT                            | Int    | Server port                                               | 9090                                             |
| ORG_GRADLE_PROJECT_isProduction | Bool   | true for production                                       | true                                             |
| baseURL                         | String | Webapp deployment base URL                                | burgr.remindr.com                                |
| BURGER_UPDATE_FREQUENCY_SECONDS | Int    | How often burgers are updated (in seconds)                | 3600                                             |
| BURGER_REQUEST_COUNT_FOURSQUARE | Int    | How many places to query at once from Foursquare (max 50) | 50                                               |
| latitudeLongitude               | String | latitude,longitude of the area we're interested in        | 41.8781,-87.6298                                 |
