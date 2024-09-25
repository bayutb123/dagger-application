package com.bayutb.core.app

object EncryptionHelper {
    private const val SHIFT = 5 // ALPHABETICAL SHIFT
    fun encrypt(text: String, shift: Int = SHIFT): String {
        val encryptedText = StringBuilder()

        for (char in text) {
            // Check if it's an uppercase letter
            if (char in 'A'..'Z') {
                // Perform the shift and wrap around within the alphabet
                val shiftedChar = ((char - 'A' + shift) % 26 + 'A'.toInt()).toChar()
                encryptedText.append(shiftedChar)
            } else if (char in 'a'..'z') {
                // For lowercase letters, do the same
                val shiftedChar = ((char - 'a' + shift) % 26 + 'a'.toInt()).toChar()
                encryptedText.append(shiftedChar)
            } else {
                // Non-alphabet characters remain unchanged
                encryptedText.append(char)
            }
        }

        return encryptedText.toString()
    }

    fun decrypt(text: String): String {
        // Decrypt by shifting in the opposite direction
        return encrypt(text, 26 - SHIFT)
    }
}