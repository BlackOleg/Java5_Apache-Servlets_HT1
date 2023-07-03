package olegivanov.service;

import olegivanov.exception.NotFoundException;
import olegivanov.model.Post;
import olegivanov.repository.PostRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostService {
  private final PostRepository repository;
  private static AtomicLong atomicLong = new AtomicLong(0);

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    Post savedPost = new Post();
    if (post.getId() > 0) {
      savedPost.setId(post.getId());
    } else {
      savedPost.setId(atomicLong.addAndGet(1));
    }
    savedPost.setContent(post.getContent());
    return repository.save(savedPost);
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

