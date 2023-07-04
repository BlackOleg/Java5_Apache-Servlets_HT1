package olegivanov.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import olegivanov.model.Post;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// Stub
public class PostRepository {

    private final static ConcurrentHashMap<Long, String> repositoryMap = new ConcurrentHashMap<>();
    private final Type itemsMapType = new TypeToken<Map<Long, String>>() {
    }.getType();

    public List<Post> all() {
        List<Post> list = repositoryMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(e -> new Post(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return list;
    }

    public Optional<Post> getById(long id) {
        Optional<Post> post = repositoryMap.entrySet().stream()
                .filter(x -> x.getKey().equals(id))
                .map(e -> new Post(e.getKey(), e.getValue()))
                .findFirst();
        return post;//Optional.ofNullable(list.orElse(null));
    }

    public Post save(Post post) {
        repositoryMap.put(post.getId(), post.getContent());
        return post;
    }

    public void removeById(long id) {
        repositoryMap.remove(id);
    }

    static Object parseHashMapToObject(HashMap map, Class cls) {
        //GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, cls);
    }
}
