package practicalWork5

import org.junit.Test
import java.lang.IllegalArgumentException
import java.time.Year
import kotlin.test.assertEquals

class LibraryServiceUnitTests {
    private val authorTomSmith = Author("Tom Smith")

    private val bookMars = Book("Mars", Genre.FANTASTIC, authorTomSmith, Year.of(1972))
    private val bookVenus = Book("Venus planet", Genre.FANTASTIC, authorTomSmith, Year.of(2001))
    private val bookMercury = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith(Stone)"), Year.of(2001))

    private val userFred = User("Fred Dart")
    private val userJames = User("James Dart")

    @Test internal fun `user name shall not be blank`() {
        var thrown = false
        try {
            User("  ")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `no duplications of users registrations`() {
        var thrown = false
        try {
            val library = Library()
            library.registerUser(User("TestUser"))
            library.registerUser(User("TestUser"))
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `book shall have correct status at adding to library`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars, Status.UsedBy(userFred))
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `no duplications of books in library`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars)
            library.addBook(bookMars)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `book's title shall not be blank`() {
        var thrown = false
        try {
            Book("  ", Genre.CRIME, authorTomSmith, Year.now())
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `author's name shall not be empty`() {
        var thrown = false
        try {
            Author(" ")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant set status for unregistered book`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars)
            library.setBookStatus(bookVenus, Status.Available)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant set status for a book used by unregistered user`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars)
            library.registerUser(userFred)
            library.setBookStatus(bookMars, Status.UsedBy(userJames))
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant set unregister a unregistered user`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars)
            library.registerUser(userFred)
            library.unregisterUser(userJames)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant take a unregistered book`() {
        var thrown = false
        try {
            val library = Library()
            library.registerUser(userJames)
            library.takeBook(userJames, bookMars)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant take a book by a unregistered user`() {
        var thrown = false
        try {
            val library = Library()
            library.addBook(bookMars)
            library.takeBook(userJames, bookMars)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `a user cant take more than 3 books`() {
        var thrown = false
        try {
            val library = Library()
            library.registerUser(userJames)

            val books : MutableList<Book> = mutableListOf()

            for (i in 1..MAX_BOOKS_PER_USER + 1) {
                val book = Book("Book$i", Genre.EDUCATION, authorTomSmith, Year.now())
                books.add(book)
                library.addBook(book)
            }

            books.forEach { book ->
                library.takeBook(userJames, book)
            }

        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant take a not available book`() {
        var thrown = false
        try {
            val library = Library()
            library.registerUser(userJames)
            library.registerUser(userFred)
            library.addBook(bookMars)
            library.takeBook(userJames, bookMars)
            library.takeBook(userFred, bookMars)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `cant get status of unregistered book`() {
        var thrown = false
        try {
            val library = Library()
            library.getBookStatus(bookVenus)
        } catch (e: IllegalStateException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test internal fun `set and get book status`() {
        val library = Library()
        library.addBook(bookMars)
        library.setBookStatus(bookMars, Status.ComingSoon)
        assertEquals(Status.ComingSoon, library.getBookStatus(bookMars))
    }

    @Test internal fun `check getAllBooks`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)

        val libList = library.getAllBooks()
        val checkList = listOf(bookMars, bookVenus)

        assert((libList.containsAll(checkList) && checkList.containsAll(libList)))
    }

    @Test internal fun `check getAllAvailableBooks`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury, Status.ComingSoon)

        val availableLibList = library.getAllAvailableBooks()
        val checkList = listOf(bookMars, bookVenus)

        assert((availableLibList.containsAll(checkList) && checkList.containsAll(availableLibList)))
    }

    @Test internal fun `check getAllBookStatuses`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus, Status.Restoration)
        library.addBook(bookMercury, Status.ComingSoon)

        val statusesList = library.getAllBookStatuses().values.toList()
        val checkList = listOf(Status.Available, Status.ComingSoon, Status.Restoration)

        assert((statusesList.containsAll(checkList) && checkList.containsAll(statusesList)))
    }

    @Test internal fun `findBooks by Genre`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury)

        val fantasticList = library.findBooks(Genre.FANTASTIC)
        val checkList = listOf(bookMars, bookVenus)

        assert((fantasticList.containsAll(checkList) && checkList.containsAll(fantasticList)))
    }

    @Test internal fun `findBooks by Year`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury)

        val list = library.findBooks(Year.of(2001))
        val expectedList = listOf(bookMercury, bookVenus)

        assert((list.containsAll(expectedList) && expectedList.containsAll(list)))
    }

    @Test internal fun `findBooks by Author`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury)

        val list = library.findBooks(authorTomSmith)
        val expectedList = listOf(bookMars, bookVenus)

        assert((list.containsAll(expectedList) && expectedList.containsAll(list)))
    }

    @Test internal fun `findBooks by substring (title)`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury)

        val list = library.findBooks(substring = "planet")
        val expectedList = listOf(bookMercury, bookVenus)

        assert((list.containsAll(expectedList) && expectedList.containsAll(list)))
    }

    @Test internal fun `findBooks by substring (author name)`() {
        val library = Library()
        library.addBook(bookMars)
        library.addBook(bookVenus)
        library.addBook(bookMercury)

        val list = library.findBooks(substring = "Smith")
        val expectedList = listOf(bookMars, bookVenus, bookMercury)

        assert((list.containsAll(expectedList) && expectedList.containsAll(list)))
    }

    @Test internal fun `general findBooks - no search parameters`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            substring = null,
            author = null,
            year = null,
            genre = null)
        val expected : List<Book> = listOf()

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }

    @Test internal fun `general findBooks by substring and year`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.COMEDY, Author("No-name Author"), Year.of(1999))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            substring = "planet",
            year = Year.of(2001))
        val expected = listOf(book1, book2)

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }

    @Test internal fun `general findBooks by substring and genre`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.HISTORICAL, Author("No-name Author"), Year.of(1999))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            substring = "planet",
            genre = Genre.HISTORICAL)
        val expected = listOf(book3)

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }

    @Test internal fun `general findBooks by author and genre`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.HISTORICAL, Author("No-name Author"), Year.of(1999))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            author = Author("No-name Author"),
            genre = Genre.COMEDY)
        val expected = listOf(book2)

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }

    @Test internal fun `general findBooks, not found case 1`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.HISTORICAL, Author("No-name Author"), Year.of(1999))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            substring = "planet",
            year = Year.of(2021),
            genre = Genre.COMEDY)
        val expected : List<Book> = listOf()

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }

    @Test internal fun `general findBooks, not found case 2`() {
        val library = Library()

        val book1 = Book("Mercury planet", Genre.COMEDY, Author("Tim Smith"), Year.of(2001))
        val book2 = Book("Venus planet", Genre.COMEDY, Author("No-name Author"), Year.of(2001))
        val book3 = Book("Mercury planet", Genre.HISTORICAL, Author("No-name Author"), Year.of(1999))

        library.addBook(book1)
        library.addBook(book2)
        library.addBook(book3)

        val found = library.findBooks(
            substring = "Smith",
            author = Author("No-name Author"),
            year = Year.of(2021),
            genre = Genre.COMEDY)
        val expected : List<Book> = listOf()

        assert((found.containsAll(expected) && expected.containsAll(found)))
    }
}
