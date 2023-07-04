package olegivanov.servlet;

import olegivanov.controller.PostController;
import olegivanov.repository.PostRepository;
import olegivanov.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    private final String METHOD_GET = "GET";
    private final String METHOD_POST = "POST";
    private final String METHOD_DEL = "DELETE";
    private final String PATH_API = "/api/posts";


    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            // primitive routing
            if (method.equals(METHOD_GET) && path.equals(PATH_API)) {
                controller.all(resp);
                return;
            }
            if (method.equals(METHOD_GET) && path.matches(PATH_API + "/\\d+")) {
                // easy way
                String parts[] =path.split("/");
                final var id = Long.parseLong(parts[parts.length-1]);
                // неверное выражение final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
                controller.getById(id, resp);
                return;
            }
            if (method.equals(METHOD_POST) && path.equals(PATH_API)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(METHOD_DEL) && path.matches(PATH_API + "/\\d+")) {
                // easy way
                String parts[] =path.split("/");
                final var id = Long.parseLong(parts[parts.length-1]);
                //final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

