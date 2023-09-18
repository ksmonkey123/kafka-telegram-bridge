package ch.awae.kafka_telegram_bridge

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class TelegramClient(val http: RestTemplate, config: BridgeProperties) {

    init {
        if (!config.telegram.token.matches(Regex("[0-9]+:[a-zA-Z0-9_-]{35}"))) {
            throw IllegalArgumentException("invalid telegram token. bad format")
        }
    }

    val url = "https://api.telegram.org/bot${config.telegram.token}/sendMessage"
    val chat = config.telegram.chatId
    val footer = config.telegram.footer

    fun sendMessage(message: String, format: TelegramMessageFormat) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val map = when (format) {
            TelegramMessageFormat.HTML -> mapOf(
                "chat_id" to chat,
                "text" to "$message\n\n--$footer",
                "parse_mode" to "HTML"
            )

            TelegramMessageFormat.MARKDOWN -> mapOf(
                "chat_id" to chat,
                "text" to "$message\n\n\\-\\-${escapeFooter(footer)}",
                "parse_mode" to "MarkdownV2"
            )
        }

        val entity = HttpEntity(map, headers)

        http.postForEntity(url, entity, Any::class.java)
    }

    val charsToEscape = listOf('_', '*', '[', ']', '(', ')', '~', '`', '>', '#', '+', '-', '=', '|', '{', '}', '.', '!')

    private fun escapeFooter(footer: String): String {
        var temp = footer
        for (char in charsToEscape) {
            temp = temp.replace("$char", "\\$char")
        }
        return temp
    }

}