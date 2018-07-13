package net.azurewebsites.pzgccxs.pzgc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.Contended;

/**
 * @author hiki on 2018-07-13
 */

@Controller(value = "/")
public class MainController {

    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
