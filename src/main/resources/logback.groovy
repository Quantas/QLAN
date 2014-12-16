import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.*

def DEFAULT_PATTERN = "%d [%thread] %-5level %logger{36} - %msg%n"

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = DEFAULT_PATTERN
    }
}

appender("FILE", FileAppender) {
    file = "log/server.log"
    append = false
    encoder(PatternLayoutEncoder) {
        pattern = DEFAULT_PATTERN
    }
}

logger("org.apache.commons.digester", INFO)
logger("org.hibernate", INFO)
logger("org.springframework", INFO)

root(DEBUG, ["STDOUT", "FILE"])