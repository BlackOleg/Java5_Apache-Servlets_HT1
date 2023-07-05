package olegivanov.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import olegivanov.model.Post;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
public class PostRepository {
    private static AtomicLong atomicLong = new AtomicLong(0);
    private final static ConcurrentHashMap<Long, Post> repositoryMap = new ConcurrentHashMap<>();


    public List<Post> all() {
        List<Post> list = repositoryMap.values().stream()
                .collect(Collectors.toList());
        return list;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(Optional.ofNullable(repositoryMap.get(id)).orElse(null));
    }

     public synchronized Post save(Post post) {
        Long newId=atomicLong.addAndGet(1);

         if (post.getId() > 0) {
            repositoryMap.put(post.getId(), post);
        } else {
            post.setId(newId);
            repositoryMap.put(newId, post);
        }
        return post;
    }

    public synchronized void removeById(long id) {
        repositoryMap.remove(id);
    }

    static Object parseHashMapToObject(HashMap map, Class cls) {
        //GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, cls);
    }
}
