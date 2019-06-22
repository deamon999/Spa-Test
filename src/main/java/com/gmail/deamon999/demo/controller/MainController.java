package com.gmail.deamon999.demo.controller;

import com.gmail.deamon999.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @Autowired
    private DataService dataService;

    @GetMapping(value = {"/", "*"})
    public String Index() {
        return "index";
    }

    @PostMapping("/UploadFile")
    public ResponseEntity uploadXmlFile(@RequestPart("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            return dataService.uploadCsvFile(file);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetTopForms")
    @ResponseBody
    public ResponseEntity getTopForms() {
        return dataService.getTopForms();
    }

    @GetMapping("/GetUsers")
    @ResponseBody
    public ResponseEntity getUsers() {
        return dataService.getUsers();
    }

    @GetMapping("/GetForms")
    @ResponseBody
    public ResponseEntity getForms(String id) {
        return dataService.getForms(id);
    }
}
