package com.buyern.entityservice.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MediaService {
    public static boolean isImage(String type) {
        return Objects.equals(type, MediaType.IMAGE_GIF_VALUE) || Objects.equals(type, MediaType.IMAGE_JPEG_VALUE) || Objects.equals(type, MediaType.IMAGE_PNG_VALUE);
    }

    public static boolean isVideo(String type) {
        return Objects.equals(type, "video/mp4") || Objects.equals(type, "video/mkv");
    }
}
