package practicalWork5

data class Author (val name: String) {
    init {
        if (name.isBlank())
            throw IllegalArgumentException("Author name shall not be empty")
    }

    override fun toString(): String {
        return "Author $name"
    }
}
