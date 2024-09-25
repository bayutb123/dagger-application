package com.bayutb.core.app

object EncryptionHelper {
    private const val KEY = "DGKEEPENCRYPTED"
    fun encrypt(text: String): String {
        val encryptedText = StringBuilder()

        for (i in text.indices) {
            val char = text[i]
            val shift = KEY[i % KEY.length].code // Variable shift based on the key

            // Shift the character within the range of printable ASCII characters (32-126)
            val shiftedChar = ((char.code - 32 + shift) % 95 + 32).toChar()
            encryptedText.append(shiftedChar)
        }

        return encryptedText.toString()
    }

    fun decrypt(text: String): String {
        val decryptedText = StringBuilder()

        for (i in text.indices) {
            val char = text[i]
            val shift = KEY[i % KEY.length].code // Use the key to reverse the shift

            // Reverse the shift within the range of printable ASCII characters (32-126)
            val shiftedChar = ((char.code - 32 - shift + 95) % 95 + 32).toChar()
            decryptedText.append(shiftedChar)
        }

        return decryptedText.toString()
    }
}