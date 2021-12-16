package practicalWork5

import java.time.Year
import java.util.logging.Logger

const val MAX_BOOKS_PER_USER = 3

class Library : LibraryService {
    private val users : MutableList<User> = mutableListOf()
    private val logger = Logger.getLogger("Library")
    private val books: MutableMap<Book, Status> = mutableMapOf()

    override fun registerUser(user: User) {
        if (users.contains(user)) {
            throw IllegalStateException("$user already registered")
        }
        users.add(user)
        logger.info("$user registered")
    }

    override fun unregisterUser(user: User) {
        if (!users.contains(user)) {
            throw IllegalStateException("$user is not registered in the library")
        }

        val usedBooks = booksUsedBy(user)

        usedBooks.forEach { book ->
            books[book] = Status.Available
            logger.info("$book is available after unregistering of $user")
        }

        logger.info("$user unregistered")
    }

    override fun addBook(book: Book, status: Status) {
        if (status is Status.UsedBy) {
            throw IllegalStateException("To add a book, it shall status available, restoration or coming soon")
        }

        if (books.contains(book)) {
            throw IllegalStateException("$book already registered in the library")
        }

        books[book] = status

        logger.info("$book added to library")
    }

    override fun takeBook(user: User, book: Book) {
        if (!users.contains(user)) {
            throw IllegalStateException("$user is not registered in the library")
        }

        if (!books.contains(book)) {
            throw IllegalStateException("$book is not registered in the library")
        }

        if (books[book] !is Status.Available) {
            throw IllegalStateException("Cant take $book, it's not available")
        }

        val usedBooks = booksUsedBy(user)

        if (usedBooks.size == MAX_BOOKS_PER_USER) {
            throw IllegalStateException("$user already took $MAX_BOOKS_PER_USER books")
        }

        books[book] = Status.UsedBy(user)

        logger.info("$user took $book")
    }

    override fun returnBook(book: Book) {
        if (!books.contains(book)) {
            throw IllegalStateException("$book not registered in the library")
        }

        books[book] = Status.Available

        logger.info("$book returned and available")
    }

    private fun booksUsedBy(user: User) : List<Book> {
        val usedBooksStatuses =  books.filter {(_, status) ->
            (status is Status.UsedBy && user == status.user)
        }
        return usedBooksStatuses.keys.toList()
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (!books.contains(book)) {
            throw IllegalStateException("$book is not registered in the library")
        }

        if (status is Status.UsedBy) {
            if (!users.contains(status.user)) {
                throw IllegalStateException("$status.user is not registered in the library")
            }
        }
        books[book] = status

        logger.info("$book status changed to $status")
    }

    override fun getAllBooks(): List<Book> {
        return books.keys.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return books.filter {(_, status) -> (status is Status.Available) }.keys.toList()
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        val copy : MutableMap<Book, Status> = mutableMapOf()
        copy.putAll(books)
        return copy
    }

    override fun getBookStatus(book: Book): Status {
        if (!books.contains(book)) {
            throw IllegalStateException("$book is not registered in the library")
        }

        return books[book]!!
    }

    override fun findBooks(substring: String): List<Book> {
        return books.keys.filter {book ->
            (book.author.name.contains(substring) || book.title.contains(substring))
        }.toList()
    }

    override fun findBooks(author: Author): List<Book> =
        books.keys.filter {book -> (book.author == author) }.toList()

    override fun findBooks(year: Year): List<Book> =
        books.keys.filter {book -> (book.year == year) }.toList()

    override fun findBooks(genre: Genre): List<Book> =
        books.keys.filter {book -> (book.genre == genre) }.toList()

    override fun findBooks(substring: String?, author: Author?, year: Year?, genre: Genre?): List<Book> {
        if (substring == null && author == null && year == null && genre == null) {
            return listOf()
        }
        return books.keys.filter {book ->
            ((substring == null || (book.author.name.contains(substring) || book.title.contains(substring))) &&
                    (author == null || book.author == author) &&
                    (year   == null || book.year   == year) &&
                    (genre  == null || book.genre  == genre))
        }.toList()
    }
}
