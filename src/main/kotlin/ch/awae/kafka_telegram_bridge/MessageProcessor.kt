package ch.awae.kafka_telegram_bridge

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class MessageProcessor(val client: TelegramClient) {

    val logger: Logger = Logger.getLogger(javaClass.name)

    @KafkaListener(topics = ["\${bridge.kafka.topic}"])
    fun handleNotificationMessage(message: String) {
        logger.info("handling message: \"${message.replace("\n", "\\n")}\"")
        client.sendMessage(message)
    }

}