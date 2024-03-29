package com.dkthanh.demo.util;

import com.dkthanh.demo.domain.dto.UploadFormDto;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class WebUtil {
    public static byte[] extractByte(String imageUrl) throws IOException {
        File file = new File(imageUrl);
        ImageInputStream iis = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }
        ImageReader reader = iter.next();
        String type = reader.getFormatName();
        iis.close();

        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, type, bos);
        byte[] data = bos.toByteArray();
        return data;
    }

    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);
        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        if(url.toString().endsWith("/")){
            url.append("/");
        }
        return url.toString();
    }

    public static String doUpload(UploadFormDto myUploadForm) {

        String description = myUploadForm.getImageName();
        System.out.println("Description: " + description);

        // Thư mục gốc upload file.
        String path = System.getProperty("user.dir");
        String uploadRelativePath = "images/project-assets/" + myUploadForm.getProjectId();
        String uploadRootPath = path + "/src/main/resources/static/" + uploadRelativePath;
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile fileDatas = myUploadForm.getFileDatas();
        // Tên file gốc tại Client.
        String name = fileDatas.getOriginalFilename();
        System.out.println("Client File Name = " + name);

        if (name != null && name.length() > 0) {
            try {
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileDatas.getBytes());
                stream.close();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + name);
            }
        }
        System.out.println("File location = " + uploadRelativePath +  File.separator + name);
        return uploadRelativePath +  File.separator + name;
    }
}
