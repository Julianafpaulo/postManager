package postManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import postManager.domain.Post;
import postManager.domain.exception.PostNotFoundException;
import postManager.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;


    public Post addPost(Post purchase) {
        return repository.save(purchase);

    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post getPost(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new PostNotFoundException(" Post not found with id " + id));

    }

    public Post updatePost(Long id, Post post) {
        return repository.findById(id)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setBody(post.getBody());
                    return repository.save(p);
                }).orElseThrow(() -> new PostNotFoundException("Post not found with id " + id));
    }
    public ResponseEntity<?> deletePost(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new PostNotFoundException("User not found with id " + id));

    }

/*
    public List<Post> getUserPosts(Long id) {
    }

    public List<coment> getPostcoments(Long id) {
    }

    public List<Post> getPostSearch(Long id) {
    }

 */
}
