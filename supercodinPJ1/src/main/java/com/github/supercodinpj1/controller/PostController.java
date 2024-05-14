package com.github.supercodinpj1.controller;

import com.github.supercodinpj1.dto.PostCreateDto;
import com.github.supercodinpj1.dto.PostResponseDto;
import com.github.supercodinpj1.dto.PostUpdateDto;
import com.github.supercodinpj1.service.PostService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody PostCreateDto dto) {
        Long postId = postService.createPost(dto);
        return ResponseEntity.ok(postId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto dto) {
        PostResponseDto updatedPost = postService.updatePost(id, dto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDto>> getPostsByAuthorEmail(@RequestParam String email) {
        List<PostResponseDto> posts = postService.getPostsByAuthorEmail(email);
        return ResponseEntity.ok(posts);
    }
}

