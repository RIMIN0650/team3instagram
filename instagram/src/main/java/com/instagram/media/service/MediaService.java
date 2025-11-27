package com.instagram.media.service;

import com.instagram.media.domain.MediaAssets;
import com.instagram.media.domain.PostMedia;
import com.instagram.media.repository.MediaAssetsRepository;
import com.instagram.media.repository.PostMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaAssetsRepository mediaAssetsRepository;
    private final PostMediaRepository postMediaRepository;

    // 로컬 업로드 폴더
    private final String uploadDir = System.getProperty("user.home") + "/instagram_uploads/post";

    public void uploadPostImages(Integer postId, List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) return;

        File dir = new File(uploadDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("업로드 폴더 생성 실패: " + dir.getAbsolutePath());
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(dir, filename);
            file.transferTo(dest);

            BufferedImage img = ImageIO.read(dest);

            // DB에는 파일명만 저장
            MediaAssets asset = new MediaAssets();
            asset.setPath(filename);
            asset.setWidth(img.getWidth());
            asset.setHeight(img.getHeight());
            asset.setMediaType(file.getContentType());
            mediaAssetsRepository.save(asset);

            PostMedia postMedia = new PostMedia();
            postMedia.setPostId(postId);
            postMedia.setMediaPath(filename);
            postMediaRepository.save(postMedia);
        }
    }

    public List<String> getPostImagePaths(Integer postId){
        return postMediaRepository.findByPostId(postId)
                .stream()
                .map(PostMedia::getMediaPath)
                .toList();
    }
}
