package patricktan.assignment.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author patricktan
 */
@Controller
public class HomeController {

    @RequestMapping("/ui")
    public String index() {
        return "index";
    }

    @RequestMapping("/ui/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/ui/company")
    public String company() {
        return "company";
    }

    @RequestMapping("/ui/owner")
    public String owner() {
        return "owner";
    }
}
