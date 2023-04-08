package ch.awae.kafka_telegram_bridge

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@ConfigurationPropertiesScan
class KafkaTelegramBridgeApplication

fun main(args: Array<String>) {
    val app = SpringApplication(KafkaTelegramBridgeApplication::class.java)
    app.webApplicationType = WebApplicationType.NONE
    app.run(*args)
}
