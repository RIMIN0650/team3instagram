package com.instagram.post.service;

import com.instagram.like.domain.Like;
import com.instagram.like.repository.LikeRepository;
import com.instagram.like.service.LikeService;
import com.instagram.post.domain.Post;
import com.instagram.post.dto.PostDetail;
import com.instagram.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeService likeService;
    private final LikeRepository likeRepository;

    // 신규 게시물 등록
    public Post addPost(String userId, String title, String textContent){
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .textContent(textContent)
                .build();

        return postRepository.save(post);
    }
    
    // 모든 게시물 불러오기
    public List<Post> getAllPost(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    
    // 사용자 아이디로 게시물 검색하기
    public List<Post> findPostByUserId(String userId){
        return postRepository.findByUserId(userId);
    }

    // 게시물 수정하기
    public Post updatePost(int postId, String title, String textContent){

        Post post = postRepository.findByPostId(postId);

        if(post != null) {
            post = post.toBuilder()
                    .title(title)
                    .textContent(textContent)
                    .updatedAt(LocalDateTime.now())
                    .build();
            post = postRepository.save(post);
        }

        return post;
    }
    
    // 게시물 아이디로 게시물 검색하기
    public Post findByPostId(int postId){
        return postRepository.findByPostId(postId);
    }

    // 게시물 삭제
    public Post deletePost(int postId){
        Post post = postRepository.findByPostId(postId);
        if(post != null){
            postRepository.delete(post);
        }
        return post;
    }



    public List<PostDetail> getPostDetailList(String loginUserId){

        List<Post> postList = postRepository.findAll();

        List<PostDetail> postDetailList = new ArrayList<>();




        for(Post post : postList){

            PostDetail postDetail = PostDetail.builder()
                    .postId(post.getPostId())
                    .userId(post.getUserId())
                    .likeCount(likeService.likeCount(post.getPostId()))
                    .dislikeCount(likeService.dislikeCount(post.getPostId()))
                    .isLike(likeService.isLikeByUser(loginUserId, post.getPostId()))
                    .isDislike(likeService.isDislikeByUser(loginUserId, post.getPostId()))
                    .title(post.getTitle())
                    .textContent(post.getTextContent())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .build();

            postDetailList.add(postDetail);




        }

        return postDetailList;
    }

    // 이 사용자가 좋아요, 싫어요 누른 게시물 찾기, 좋아요 : 1, 싫어요 : 0
    public List<Post> findPostLikeByUser(String userId, int likeStatus){
        List<Like> likeList = likeRepository.findByLikeStatusAndUserId(likeStatus, userId);
        List<Post> postList = new ArrayList<>();
        for(Like like : likeList){
            Post post = postRepository.findByPostId(like.getPostId());
            postList.add(post);
        }
        return postList;
    }



    // 게시글 상세 정보
    public PostDetail getPostDetail(int postId, String loginUserId) {
        Post post = postRepository.findByPostId(postId);

        PostDetail postDetail = PostDetail.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .likeCount(likeService.likeCount(post.getPostId()))
                .dislikeCount(likeService.dislikeCount(post.getPostId()))
                .isLike(likeService.isLikeByUser(loginUserId, post.getPostId()))
                .isDislike(likeService.isDislikeByUser(loginUserId, post.getPostId()))
                .title(post.getTitle())
                .textContent(post.getTextContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
        return postDetail;
    }


    public List<Post> getTop5HotPosts(){

        List<Post> postList = postRepository.findAll();

        int size = postList.size();
        int[][] countLike = new int[postList.size()][2];

        for(int i = 0; i < postList.size(); i++){
            int postId = postList.get(i).getPostId();
            countLike[i][0] = postId;
            countLike[i][1] = likeRepository.countByPostIdAndLikeStatus(postId, 1);
        }

        Arrays.sort(countLike, (a, b) -> Integer.compare(b[1], a[1]));

        List<Post> top5LikePostList = new ArrayList<>();
        int resultSize = Math.min(5, size);

        for(int i = 0; i < resultSize; i++){
            Post post = postRepository.findByPostId(countLike[i][0]);
            top5LikePostList.add(post);
        }
        return top5LikePostList;
    }


}
