package com.sztop.battlefield.jpa.service;

import com.sztop.battlefield.jpa.model.Comment;
import com.sztop.battlefield.jpa.model.Post;
import com.sztop.battlefield.jpa.repository.CommentRepository;
import com.sztop.battlefield.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Cacheable(cacheNames = "PostsWithComments")
    public List<Post> getPostsWithComments(int pageNumber, Sort.Direction sort) {
        List<Post> posts = postRepository.findAllPosts(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        posts.forEach(post -> post.setComment(extractCommentsById(comments, post.getId())));
        return posts;
    }

    private List<Comment> extractCommentsById(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getId() == id)
                .collect(Collectors.toList());
    }

    public List<Post> getPosts(int pageNumber, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sort, "id")));
    }

    @Cacheable(cacheNames = "SinglePost", key = "#id") // all parameters included by default
    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    //result is a Post which returns the method, id is a key because genSinglePost uses id as the key
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        return postEdited;
    }

    @CacheEvict(cacheNames = "SinglePost", key = "#id")
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "PostsWithComments")
    public void clearPostsWithComments() {
    //Wywołana tutaj funkcja by nie wyczyscila cache bo aspekt by nie zadziałał. Musi zostac wywoalana z innego beana
    }
}
