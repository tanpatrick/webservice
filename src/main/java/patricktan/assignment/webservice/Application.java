package patricktan.assignment.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import patricktan.assignment.webservice.domain.Company;
import patricktan.assignment.webservice.domain.Owner;

/**
 *
 * @author patricktan
 */
@SpringBootApplication
public class Application extends RepositoryRestMvcConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js")
                .addResourceLocations("classpath:/static/js");

        super.addResourceHandlers(registry);
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Company.class);
        config.exposeIdsFor(Owner.class);
    }
}
