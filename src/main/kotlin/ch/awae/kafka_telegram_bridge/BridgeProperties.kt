package ch.awae.kafka_telegram_bridge

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bridge")
data class BridgeProperties(
    val kafka: KafkaProperties,
    val telegram: TelegramProperties,
)

data class KafkaProperties(
    val topic: String,
    val groupId: String,
    val bootstrapServers: String,
)

data class TelegramProperties(
    val token: String,
    val chatId: Long,
    val footer: String,
)