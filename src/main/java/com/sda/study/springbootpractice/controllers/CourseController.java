package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.models.Course;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.services.CourseService;
import com.sda.study.springbootpractice.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Tiina Raudla
 * @Date
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String showCourseListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("courses", courseService.findAllCourses());
        return "courses/list-course";

    }
    @GetMapping("/{id}")
    public String showCourseViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("course", courseService.findCourseById(id));
            return "course/view-course";
        } catch (CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourseById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Course #%d deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/course";
        } catch (CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    @GetMapping("/create")
    public String showCreateCoursePage(@ModelAttribute("course") Course course,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "course/create-course";
    }
    // Called when we press submit button in the create-course form
    @PostMapping
    public String createCourse(Course course, RedirectAttributes redirectAttributes) {
        try {
            Course searchCourse = courseService.findCourseByName(course.getName());
            redirectAttributes.addFlashAttribute("message", String.format("Course(%s) already exists!", course.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/course/create-course";
        } catch (CourseNotFoundException e) {
            courseService.createCourse((course));
            redirectAttributes.addFlashAttribute("message", String.format("Course(%s) has been created successfully!", course.getName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/course";
        }
    }
    @GetMapping("/update/{id}")
    public String showUpdateCoursePage(@PathVariable Long id, RedirectAttributes redirectAttributes,
                                       @RequestParam(value="course", required = false) Course course,
                                       Model model) {
        if(course == null) {
            try {
                model.addAttribute("course", courseService.findCourseById(id));
            } catch (CourseNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "course/update-course";
    }
    @PostMapping("/update")
    public String updateCourse(Course course, RedirectAttributes redirectAttributes) {
        try {
            courseService.updateCourse(course);
            redirectAttributes.addFlashAttribute("message", String.format("Course(id=%d) has been updated successfully!", course.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return"redirect:/course";
        } catch (CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    @GetMapping("/restore/{id}")
    public String restoreCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.restoreCourseById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Course #%d restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/course";
        } catch (CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    // PRIVATE METHODS //
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/course";
    }
}
