package eu.tutorials.loginandsignupwithfirebase

data class User(
    var name: String,
    var specialization: String,
    var description: String,
    var imageId: Int,  // Changed from `imageID` to `imageId`
    var rating: String
)

