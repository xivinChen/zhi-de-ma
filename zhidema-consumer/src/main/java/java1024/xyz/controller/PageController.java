package java1024.xyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping(value = "/{page}.html",method = RequestMethod.GET)
    public ModelAndView page(@PathVariable("page") String page) {

        System.out.println("进入 page");
        ModelAndView modelAndView = new ModelAndView(page);
        return modelAndView;

    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index() {

        System.out.println("/进入index");
        ModelAndView modelAndView = new ModelAndView("layuiAdmin");
        return modelAndView;

    }

}
