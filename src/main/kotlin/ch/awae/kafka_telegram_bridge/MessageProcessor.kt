package ch.awae.kafka_telegram_bridge

import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
@KafkaListener(topics = ["\${bridge.kafka.topic}"], containerFactory = "messageKafkaListenerContainerFactory")
class MessageProcessor(val client: TelegramClient) {

    val logger: Logger = Logger.getLogger(javaClass.name)

    @KafkaHandler
    fun handleNotificationMessage(message: TelegramMessage) {
        logger.info("handling ${message.format} message: \"${message.text.replace("\n", "\\n")}\"")
        client.sendMessage(message.text, message.format)
    }

    @KafkaHandler
    fun handleStringMessage(message: String) {
        logger.info("handling basic message: \"${message.replace("\n", "\\n")}\"")
        client.sendMessage(message, TelegramMessageFormat.HTML)
    }

}