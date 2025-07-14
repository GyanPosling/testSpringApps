package myTaskManager.controllers;

import myTaskManager.dao.TaskManagerDAO;
import myTaskManager.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/taskManager")
public class TaskManagerController {

    private final TaskManagerDAO taskManagerDAO;

    @Autowired
    public TaskManagerController(TaskManagerDAO taskManagerDAO) {
        this.taskManagerDAO = taskManagerDAO;
    }

    @GetMapping()
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskManagerDAO.getAllTasks());
        return "showAllTasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable int id, Model model) {
        model.addAttribute("task", taskManagerDAO.getTaskById(id));
        return "showTask";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task) {
        return "newTask";
    }

    @PostMapping()
    public String createTask(@ModelAttribute("task") @Valid Task task,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "newTask";
        }
        taskManagerDAO.saveTask(task);
        return "redirect:/taskManager";
    }

    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable int id, Model model) {
        model.addAttribute("task", taskManagerDAO.getTaskById(id));
        return "editTask";
    }

    @PatchMapping("/{id}")
    public String updateTask(@ModelAttribute("task") @Valid Task task,
                             @PathVariable int id,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "editTask";
        }
        taskManagerDAO.updateTask(id, task);
        return "redirect:/taskManager";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        taskManagerDAO.deleteTask(id);
        return "redirect:/taskManager";
    }

    @DeleteMapping
    public String deleteAllTasks() {
        taskManagerDAO.deleteAllTasks();
        return "redirect:/taskManager";
    }

    @GetMapping("/sortByPriority")
    public String sortByPriority(Model model) {
        model.addAttribute("tasks", taskManagerDAO.sortByPriority());
        model.addAttribute("sort", "priority");
        return "showAllTasks";
    }


    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        model.addAttribute("tasks", taskManagerDAO.getCompletedTasks());
        model.addAttribute("sort", "completed");
        return "showAllTasks";
    }

    @GetMapping("/uncompleted")
    public String getUncompletedTasks(Model model) {
        model.addAttribute("tasks", taskManagerDAO.getUncompletedTasks());
        model.addAttribute("sort", "uncompleted");
        return "showAllTasks";
    }
}
