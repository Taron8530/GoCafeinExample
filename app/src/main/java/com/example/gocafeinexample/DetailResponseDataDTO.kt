package com.example.gocafeinexample

data class DetailResponseDataDTO(
    var Actors: String,
    var Awards: String,
    var BoxOffice: String,
    var Country: String,
    var DVD: String,
    var Director: String,
    var Genre: String,
    var Language: String,
    var Metascore: String,
    var Plot: String,
    var Poster: String,
    var Production: String,
    var Rated: String,
    var Ratings: List<Rating>,
    var Released: String,
    var Response: String,
    var Runtime: String,
    var Title: String,
    var Type: String,
    var Website: String,
    var Writer: String,
    var Year: String,
    var imdbID: String,
    var imdbRating: String,
    var imdbVotes: String
) {
    data class Rating(
        var Source: String,
        var Value: String
    )
}