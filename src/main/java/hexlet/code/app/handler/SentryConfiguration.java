package hexlet.code.app.handler;

import io.sentry.spring.jakarta.EnableSentry;
import org.springframework.context.annotation.Configuration;

@EnableSentry(dsn = "https://4e186447b859a7085198bca762f885fb@o4508321560199168.ingest.de.sentry.io/4508325729009744")
@Configuration
class SentryConfiguration {
}
