package practicalWork5

data class User (val name: String) {
    init {
        if (name.isBlank())
            throw IllegalArgumentException("User name shall not be blank")
    }

    override fun toString(): String {
        return "User '$name'"
    }
}
