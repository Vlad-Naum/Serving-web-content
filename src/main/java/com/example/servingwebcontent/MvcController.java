package com.example.servingwebcontent;

import com.example.servingwebcontent.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MvcController {
    @Autowired
    private MessageRepo messageRepo;

    /*@PostMapping
    public String addNewMessage (@RequestParam String text, Model model){
        Message message = new Message();
        message.setText(text);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("text", text);
        return "Saved";
    }*/

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
}
