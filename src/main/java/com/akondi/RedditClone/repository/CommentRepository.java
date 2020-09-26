package com.akondi.RedditClone.repository;

import com.akondi.RedditClone.model.Comment;
import com.akondi.RedditClone.model.Post;
import com.akondi.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
