package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Log
@Controller
public class MainController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    String baseUrlRedirect() {
        return "Hello!";
    }
}


