package olegivanov.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import olegivanov.model.Post;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
public class PostRepository {

    private static ConcurrentHashMap<Long, String> repositoryMap = new ConcurrentHashMap<>();
    private Type itemsMapType = new TypeToken<Map<Long, String>>() {
    }.getType();

    public List<Post> all() {
        var map =
                repositoryMap.entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue())
                );

        return (List<Post>) map; //Collections.emptyList();
    }

    public Optional<Post> getById(long id) {
        var map =
                repositoryMap.entrySet().stream()
                        .filter(x -> x.getKey() == id)
                        .collect(Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> entry.getValue())
                        );

        return Optional.empty();
    }

    public Post save(Post post) {
        repositoryMap.put(post.getId(), post.getContent());

        //todo
        return post;
    }

    public void removeById(long id) {
    }

    static Object parseHashMapToObject(HashMap map, Class cls) {
        //GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ;
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, cls);
    }
}
