package postManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postManager.domain.Post;
import postManager.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }

 /*   @GetMapping ("/posts/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getUserPosts(@PathVariable("userid") Long id){
        List <Post> post = postService.getUserPosts(id);
        return post;
    }

    @GetMapping ("/{id}/coments")
    @ResponseStatus(HttpStatus.OK)
    public List<coment> getPostcoments(@PathVariable("id") Long id){
        List <coment> coments = postService.getPostcoments(id);
        return coments;
    }

    @GetMapping ("/search")
    @ResponseStatus(HttpStatus.OK)
    public List <Post> getPostSearch(@PathVariable("id") Long id){
        List <Post> posts = postService.getPostSearch(id);
        return posts;
    }
*/
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody Post post,@PathVariable Long id){
        postService.updatePost(id,post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

}