package com.ratracks.utils.string;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class UrlBuilder {
    private final Environment env;

    public UrlBuilder(Environment env) {
        this.env = env;
    }

    public String buildCorreiosApiUrl(String path) {
        String urlCorreios = env.getProperty("api.correios.url");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(urlCorreios);
        strBuilder.append(path);
        return strBuilder.toString();
    }
}
