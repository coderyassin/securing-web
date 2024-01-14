package org.yascode.securingweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("connect")
public class ConnectController {

    @GetMapping("/start")
    public String start(){
        return "this show connect controller";
    }

}
