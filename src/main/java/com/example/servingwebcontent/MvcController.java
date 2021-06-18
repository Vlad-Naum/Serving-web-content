package com.example.servingwebcontent;

import com.example.servingwebcontent.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class MvcController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "all";
    }

    @PostMapping("/add")
    public String addNewMessage (@ModelAttribute("text") String text){
        System.out.println(text);
        Message message = new Message();
        message.setText(text);
        messageRepo.save(message);
        System.out.println("Message add.");
        return "index";
    }


    @PostMapping("/all")
    public String deleteMessage(@ModelAttribute("text") String text){
        try {
            int id = Integer.parseInt(text);
            Iterable<Message> messages = messageRepo.findAll();
            for (Message message : messages) {
                if(message.getId() == id){
                    messageRepo.delete(message);
                    break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "all";
    }

    @GetMapping("/bye")
    public String bye(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "bye";
    }
}
