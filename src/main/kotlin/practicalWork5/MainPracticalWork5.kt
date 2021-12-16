package practicalWork5

import java.time.Year
import java.util.logging.Logger

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val logger = Logger.getLogger("Librarian")
    val library = Library()

    val userFred = User("Fred")
    val userJames = User("James")
    library.registerUser(userFred)
    library.registerUser(userJames)

    val bookMarsByVi  = Book("Mars", Genre.COMEDY, Author("Tom Vi"), Year.of(1970))
    val bookMarsByCo  = Book("Mars", Genre.HISTORICAL, Author("Mr. Co"), Year.of(1984))
    val bookMarsByCo2 = Book("Mars (Second Edition)", Genre.HISTORICAL, Author("Mr. Co"), Year.of(2011))

    library.addBook(bookMarsByVi)
    library.addBook(bookMarsByCo, Status.Restoration)
    library.addBook(bookMarsByCo2)

    library.takeBook(userFred, bookMarsByVi)
    library.returnBook(bookMarsByVi)

    try {
        library.takeBook(userFred, bookMarsByCo)
    } catch (e:IllegalStateException) {
        logger.warning("$userFred can' take $bookMarsByCo as it on restoration")
    }

    library.setBookStatus(bookMarsByCo, Status.Available)
    library.takeBook(userFred, bookMarsByVi)
    library.takeBook(userFred, bookMarsByCo)
    library.takeBook(userJames, library.getAllAvailableBooks()[0])

    if (library.getAllAvailableBooks().isNotEmpty()) {
        throw java.lang.IllegalStateException("There are shall not be available books")
    }

    library.unregisterUser(userFred)

    var available = library.getAllAvailableBooks()
    var shallBeAvailable = listOf(bookMarsByVi, bookMarsByCo)
    if (!(available.containsAll(shallBeAvailable) && shallBeAvailable.containsAll(available))) {
        throw java.lang.IllegalStateException("There are shall be available books")
    }

    library.returnBook(bookMarsByCo2)

    available = library.getAllAvailableBooks()
    shallBeAvailable = listOf(bookMarsByVi, bookMarsByCo, bookMarsByCo2)
    if (!(available.containsAll(shallBeAvailable) && shallBeAvailable.containsAll(available))) {
        throw java.lang.IllegalStateException("There are shall be available books")
    }

    var books : List<Book> = library.findBooks("Second Edition")
    if (books.size != 1 || books[0] != bookMarsByCo2) {
        throw java.lang.IllegalStateException("bookMarsByCo2 shall be found")
    }

    books =  library.findBooks(substring = "Mars", year = Year.of(2011))

    if (books.size != 1 || books[0] != bookMarsByCo2) {
        throw java.lang.IllegalStateException("bookMarsByCo2 shall be found")
    }
}
