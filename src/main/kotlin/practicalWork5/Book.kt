package practicalWork5

import java.time.Year

enum class Genre {
    COMEDY,
    CRIME,
    EDUCATION,
    FANTASTIC,
    HISTORICAL,
}

data class Book(val title: String, val genre: Genre, val author: Author, val year: Year) {
    init {
        if (title.isBlank())
            throw IllegalArgumentException("Book's title shall not be blank")
    }

    override fun toString(): String {
        return "Book -'$title, $author, $year, ($genre)-"
    }
}
