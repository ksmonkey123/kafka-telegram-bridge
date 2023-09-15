package ch.awae.kafka_telegram_bridge

enum class TelegramMessageFormat {
    HTML, MARKDOWN
}

class TelegramMessage {
    lateinit var text: String
    var format: TelegramMessageFormat = TelegramMessageFormat.HTML
}