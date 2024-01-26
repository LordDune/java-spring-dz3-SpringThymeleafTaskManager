package ru.ld.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ld.demo.Models.Task;
import ru.ld.demo.Services.Service;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    Service service;

    @Value("${home_page_name}")  // связь с properties, берем оттуда значения и передаем их в метод
    private String homePage;

    @Value("${greetings1}")  // связь с properties, берем оттуда значения и передаем их в метод
    private String greetings1;

    @Value("${greetings2}")  // связь с properties, берем оттуда значения и передаем их в метод
    private String greetings2;

    @GetMapping()
    public String main(Model model) {
        model.addAttribute("home_page_name", homePage); // используем переменную homePage, чтобы поставить в шаблон
        model.addAttribute("greetings1", greetings1);
        model.addAttribute("greetings2", greetings2); // используем переменную greetings, чтобы поставить в шаблон
        return "index";
    }

    @GetMapping("/tasks")
    public String getAll(Model model, Task task) {
        List<Task> tasks = service.getAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String create(Task task) {
        service.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("status/{id}")
    String deleteUser(@PathVariable("id") int id){
        service.complete(id);
        return "redirect:/tasks";
    }
}
