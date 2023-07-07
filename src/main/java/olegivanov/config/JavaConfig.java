package olegivanov.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import olegivanov.controller.PostController;
import olegivanov.repository.PostRepository;
import olegivanov.repository.PostRepositoryStubImpl;
import olegivanov.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    // аргумент метода и есть DI
    // название метода - название бина
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryStubImpl();
    }
}
