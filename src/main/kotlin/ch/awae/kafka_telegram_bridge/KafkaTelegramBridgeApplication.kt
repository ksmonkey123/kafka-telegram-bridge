package ch.awae.kafka_telegram_bridge

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableKafka
@ConfigurationPropertiesScan
class KafkaTelegramBridgeApplication {

    @Bean
    fun restTemplate() = RestTemplate()

}

fun main(args: Array<String>) {
    val app = SpringApplication(KafkaTelegramBridgeApplication::class.java)
    app.webApplicationType = WebApplicationType.NONE
    app.run(*args)
}
