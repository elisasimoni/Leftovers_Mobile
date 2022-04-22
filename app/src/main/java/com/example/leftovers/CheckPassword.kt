import android.util.Log
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object CheckPassword {
    @JvmStatic
    fun main(args: Array<String>) {
        /* Plain text Password. */
        val password = "myNewPass123"

        /* generates the Salt value. It can be stored in a database. */
        val saltvalue = PassBasedEnc.getSaltvalue(30)

        /* generates an encrypted password. It can be stored in a database.*/
        val encryptedpassword = PassBasedEnc.generateSecurePassword(password, saltvalue)

        /* Print out plain text password, encrypted password and salt value. */println("Plain text password = $password")
        println("Secure password = $encryptedpassword")
        println("Salt value = $saltvalue")

        /* verify the original password and encrypted password */
        val status = PassBasedEnc.verifyUserPassword(password, encryptedpassword, saltvalue)
        if (status) println("Password Matched!!") else println("Password Mismatched")
    }

    fun createPassword(password: String): String? {
        /* generates the Salt value. It can be stored in a database. */
        val saltvalue = PassBasedEnc.getSaltvalue(30)

        /* generates an encrypted password. It can be stored in a database.*/
        return PassBasedEnc.generateSecurePassword(password, saltvalue)
    }

    fun comparePassword(password: String, encryptedpassword: String?, saltvalue: String): Boolean {
        val status = PassBasedEnc.verifyUserPassword(password, encryptedpassword, saltvalue)
        return if (status) true else false
    }
}

internal object PassBasedEnc {
    /* Declaration of variables */
    private val random: Random = SecureRandom()
    private const val characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    private const val iterations = 10000
    private const val keylength = 256

    /* Method to generate the salt value. */
    fun getSaltvalue(length: Int): String {
        val finalval = StringBuilder(length)
        for (i in 0 until length) {
            finalval.append(characters[random.nextInt(characters.length)])
        }
        return String(finalval)
    }

    /* Method to generate the hash value */
    fun hash(password: CharArray?, salt: ByteArray?): ByteArray {
        val spec = PBEKeySpec(password, salt, iterations, keylength)
        if (password != null) {
            Arrays.fill(password, Character.MIN_VALUE)
        }
        return try {
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            skf.generateSecret(spec).encoded
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } catch (e: InvalidKeySpecException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } finally {
            spec.clearPassword()
        }
    }

    /* Method to encrypt the password using the original password and salt value. */
    fun generateSecurePassword(password: String, salt: String): String? {
        val finalval: String?
        val securePassword = hash(password.toCharArray(), salt.toByteArray())
        finalval = Base64.getEncoder().encodeToString(securePassword)
        return finalval
    }

    /* Method to verify if both password matches or not */
    fun verifyUserPassword(
        providedPassword: String,
        securedPassword: String?, salt: String
    ): Boolean {
        val finalval: Boolean
        Log.w("temp", salt)
        /* Generate New secure password with the same salt */
        val newSecurePassword = generateSecurePassword(providedPassword, salt)
        Log.w("temp1", newSecurePassword!!)
        Log.w("temp2", securedPassword!!)
        /* Check if two passwords are equal */finalval =
            newSecurePassword.equals(securedPassword, ignoreCase = true)
        return finalval
    }
}