## KMM Sample application
Kotlin Multiplatform for Mobile project to make Android and iOS apps sharing a common domain & data layers

# App structure
Multiplatform application with shared logic.
Fetching the list of products with Ktor and saving the result into the SQLDelight database to get the product by ID when click to see the detail screen.
The ViewModel is shared with flowable calls and covered by Combined ViewModel in iOS framework.