package com.example.servingwebcontent;

import com.example.servingwebcontent.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(path="/main") // This means URL's start with /demo (after Application path)

public class MvcController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "all";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/bye")
    public String bye(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "bye";
    }

    @PostMapping("/add")
    public String addNewMessage (@ModelAttribute("text") String text){
        System.out.println(text);
        Message message = new Message();
        message.setText(text);
        messageRepo.save(message);
        System.out.println("Yes");
        return "index";
    }

}
