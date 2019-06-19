package lk.pos.springmvc.finalapp.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(JPAConfig.class)
@Configuration
public class WebRootconfig {
}
