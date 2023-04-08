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

    fun sendMessage(message: String) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val map = mapOf(
            "chat_id" to chat,
            "text" to "$message\n\n--$footer"
        )

        val entity = HttpEntity(map, headers)

        http.postForEntity(url, entity, Any::class.java)
    }

}