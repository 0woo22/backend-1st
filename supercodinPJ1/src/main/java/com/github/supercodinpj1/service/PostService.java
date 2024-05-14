package com.github.supercodinpj1.service;

import com.github.supercodinpj1.domain.Post;
import com.github.supercodinpj1.dto.PostCreateDto;
import com.github.supercodinpj1.dto.PostResponseDto;
import com.github.supercodinpj1.dto.PostUpdateDto;
import com.github.supercodinpj1.respository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Long createPost(PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostUpdateDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다. ID=" + id));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreatedAt());
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다. ID=" + id));
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreatedAt())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByAuthorEmail(String email) {
        List<Post> posts = postRepository.findByAuthor(email);
        return posts.stream().map(post -> new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCreatedAt())).collect(Collectors.toList());
    }
}

