package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.google.common.primitives.Longs;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.Utility;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Arrays;

@Controller()
//@RequestMapping("/home")
public class FileController {

    FileService fileService;
    UserService userService;

    private final String UPLOAD_DIR = "./uploads/";

    public FileController(FileService fileService, UserService userService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("files", this.fileService.getAllFiles());
        return "home";
    }

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileId) {

        com.udacity.jwdnd.course1.cloudstorage.model.File newFile = fileService.getOneFile(fileId);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + newFile.getFilename());
        header.add("Cache-control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource((newFile.getFileData()));
        return ResponseEntity.ok()
                .headers(header)
                .body(resource);
    }


    @RequestMapping(value = "/home/{fileId}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable(name = "fileId") String fileId) {
        fileService.deleteFile(Integer.valueOf(fileId));
        return "redirect:/home";
    }

    @PostMapping("/home")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Principal principal) {

//        System.out.println(principal.getName());
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/home";
        }

        String result = "";
        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File myFile = Utility.multipartToFile(file);
            String mimeType = URLConnection.guessContentTypeFromName(myFile.getName());
            User loggedInUser = userService.getUser(principal.getName());
            long si = Files.size(path);
            byte[] bytes = Longs.toByteArray(si);
            com.udacity.jwdnd.course1.cloudstorage.model.File newFile = new com.udacity.jwdnd.course1.cloudstorage.model.File(fileName, mimeType, file.getSize() * 0.0009765625 + "  kb", loggedInUser.getUserid(), bytes);
            System.out.println(newFile);
            result = fileService.saveFile(newFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // return success response
        attributes.addFlashAttribute("message", result +" "+ fileName + '!');

        return "redirect:/home";
    }

}
