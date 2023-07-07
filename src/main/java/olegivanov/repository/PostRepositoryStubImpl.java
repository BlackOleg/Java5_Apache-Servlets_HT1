package olegivanov.repository;

import olegivanov.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
        private static AtomicLong atomicLong = new AtomicLong(0);
    private final static ConcurrentHashMap<Long, Post> repositoryMap = new ConcurrentHashMap<>();


    public List<Post> all() {
        List<Post> list = new ArrayList<>(repositoryMap.values());
        return list;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(Optional.ofNullable(repositoryMap.get(id)).orElse(null));
    }

    public Post save(Post post) {
        Long newId = atomicLong.addAndGet(1);

        if (post.getId() > 0) {
            repositoryMap.put(post.getId(), post);
        } else {
            post.setId(newId);
            repositoryMap.put(newId, post);
        }
        return post;
    }

    public void removeById(long id) {
        repositoryMap.remove(id);
    }
}
