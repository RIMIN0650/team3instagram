package com.instagram.config;

import com.instagram.media.domain.MediaAssets;
import com.instagram.media.domain.PostMedia;
import com.instagram.media.repository.MediaAssetsRepository;
import com.instagram.media.repository.PostMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final MediaAssetsRepository mediaAssetsRepository;
    private final PostMediaRepository postMediaRepository;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 로컬 'instagram_uploads/post' 폴더를 /images/** URL로 매핑
        String uploadPath = System.getProperty("user.home") + "/instagram_uploads/post/";
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadPath);
    }


//    public void uploadPostImages(Integer postId, List<MultipartFile> images) throws IOException {
//        String uploadDir = "uploads/post/";
//
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        for (MultipartFile image : images) {
//            if (image.isEmpty()) continue;
//
//            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
//            File dest = new File(dir, filename);
//
//            image.transferTo(dest);
//
//
//            BufferedImage img = ImageIO.read(dest);
//            MediaAssets asset = new MediaAssets();
//            asset.setPath(dest.getAbsolutePath());
//            asset.setWidth(img.getWidth());
//            asset.setHeight(img.getHeight());
//            asset.setMediaType(image.getContentType());
//            mediaAssetsRepository.save(asset);
//
//            PostMedia postMedia = new PostMedia();
//            postMedia.setPostId(postId);
//            postMedia.setMediaPath(dest.getAbsolutePath());
//            postMediaRepository.save(postMedia);
//        }
//    }



}
