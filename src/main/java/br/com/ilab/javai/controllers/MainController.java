package br.com.ilab.javai.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.ilab.javai.util.S3DownloadFile;
import br.com.ilab.javai.util.S3UploadFile;

@Controller
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUploadForm(Model model, String description,
            @RequestParam("file") MultipartFile multipart) {
        String fileName = multipart.getOriginalFilename();

        System.out.println("Description: " + description);
        System.out.println("filename: " + fileName);

        String message = "";

        try {
            S3UploadFile.uploadFile(fileName, multipart.getInputStream());
            message = "Your file has been uploaded successfully!";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName, Model model) throws IOException {
        S3DownloadFile.downloadFile(fileName);

        model.addAttribute("download", fileName);

        return "download";
    }

}