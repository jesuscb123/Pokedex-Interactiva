package dam2.jetpack.pokedex.data.security

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

object PasswordHasher {

    private const val SALT_LENGTH = 16

    fun generateSalt(): String {
        val bytes = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(bytes)
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun hash(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest((password + salt).toByteArray())
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun verify(password: String, salt: String, expectedHash: String): Boolean {
        return hash(password, salt) == expectedHash
    }
}
