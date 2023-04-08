package ch.awae.kafka_telegram_bridge

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.web.client.RestTemplate

@EnableKafka
@Configuration
class BridgeConfiguration {

    @Bean
    fun consumerFactory(config: BridgeProperties) : ConsumerFactory<String, String> {
        val map = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to config.kafka.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to config.kafka.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        )
        return DefaultKafkaConsumerFactory(map)
    }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, String>) : ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory
        return factory
    }

    @Bean
    fun restTemplate() = RestTemplate()

}