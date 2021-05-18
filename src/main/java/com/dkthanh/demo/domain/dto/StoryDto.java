package com.dkthanh.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryDto {
    protected Integer projectId;
    protected String storyDes;
    protected MultipartFile fileDatas;

    public StoryDto(Integer projectId, String storyDes) {
        this.projectId = projectId;
        this.storyDes = storyDes;
    }
}
