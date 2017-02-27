package ru.puzino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Puzino Yury on 27.02.2017.
 */
@Controller
public class HomePageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
