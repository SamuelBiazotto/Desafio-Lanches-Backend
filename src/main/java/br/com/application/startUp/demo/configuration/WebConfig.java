package br.com.application.startUp.demo.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 /**
 * A class to configure cors to not get any error with cors filter. This class is usually utilized to
 * not get errors with requests made by localhost.
 *
 * @author Samuel Biazotto de Oliveira
 **/

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * This method add permisions for requests directed for this server. The methods GET, POST, OPTIONS and UPDATE
     * are permited, beyond the headers "Content-Type", "X-Requested-With", "accept", "Origin",
     * "Access-Control-Request-Method", "Access-Control-Request-Headers. The header permited to be seen in the response are
     * "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials".
     *
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
    }
}
