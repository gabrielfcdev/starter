package br.com.gfctech.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public void createPost(long userId, String content)

    {
        UserEntity user = userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));

         PostEntity newPost = new PostEntity();
         newPost.setAuthor(user);
         newPost.setContent(content);
         postRepository.save(newPost);


    }

    public void commentOnPost(Long userId, Long postId, String comment)
    {
        UserEntity user = userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = postRepository.findById(postId)
         .orElseThrow(() -> new RuntimeException("Post not found"));

        post.addComment(comment);(user, comment);
        postRepository.save(post);
    }

    public void likePost(Long userId, Long postId)
    {
        UserEntity user = userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = postRepository.findById(postId)
         .orElseThrow(() -> new RuntimeException("Post not found"));

        post.addLike(user);
        postRepository.save(post);
    }

}
