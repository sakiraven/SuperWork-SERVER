package com.saki.work.module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CourseInfoDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("uploader")
    private Uploader uploader;

    @NoArgsConstructor
    @Data
    public static class Uploader {
        /**
         * miiImage
         */
        @JsonProperty("mii_image")
        private String miiImage;
    }

    @JsonProperty("tags_name")
    private List<String> tagsName;
}
