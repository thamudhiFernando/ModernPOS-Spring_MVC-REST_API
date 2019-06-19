package lk.pos.springmvc.finalapp.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = "lk.pos.springmvc.finalapp")
@Configuration
@EnableWebMvc
public class WebAppConfig {

}
