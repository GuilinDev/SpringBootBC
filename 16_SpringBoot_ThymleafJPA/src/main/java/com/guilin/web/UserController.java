package com.guilin.web;

import com.guilin.model.User;
import com.guilin.param.UserParam;
import com.guilin.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    // JPA 依赖 Pageable 为用户列表页做分页，默认每页展示 6 个用户，并且按照用户注册的倒序来排列
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> users = userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, Model model) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/userAdd";
        }
        User u = userRepository.findByUserName(userParam.getUserName());
        if (u != null) {
            model.addAttribute("errorMsg", "用户已存在!");
            return "user/userAdd";
        }
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user = userRepository.findById((long) id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result, Model model) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "redirect:/list";
    }
}