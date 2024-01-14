package org.yascode.securingweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subscribers")
public class SubscribersController {

    @GetMapping("/start")
    public String start(){
        return "this show subscribers controller";
    }

}
