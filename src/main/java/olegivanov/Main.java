package olegivanov;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import olegivanov.service.PostService;

import javax.servlet.http.HttpServletResponse;

public class Main {

//    public static void main(String[] args) {
//        // отдаём список пакетов, в которых нужно искать аннотированные классы
//        final var context = new AnnotationConfigApplicationContext("olegivanov");
//
//        // получаем по имени бина
//        final var controller = context.getBean("postController");
//
//        // получаем по классу бина
//        final var service = context.getBean(PostService.class);
//
//        // по умолчанию создаётся лишь один объект на BeanDefinition
//        final var isSame = service == context.getBean("postService");
//        final var repository = context.getBean("postRepositoryStubImpl");
//
//    }
}