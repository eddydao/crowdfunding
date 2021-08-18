package com.dkthanh.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadFormDto {
    protected Integer projectId;
    protected String imageName;
    protected MultipartFile fileDatas;

    public UploadFormDto (Integer projectId, MultipartFile fileDatas){
        this.projectId = projectId;
        this.fileDatas = fileDatas;
    }

}
