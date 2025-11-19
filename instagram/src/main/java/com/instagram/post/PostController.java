package com.instagram.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/post/newpost")
    public String getPostUploadForm(){
        return "post/upload";
    }

    @GetMapping("/post/")
    public String getPostSearchByUserId(){

    }


}
