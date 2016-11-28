package ua.vesa.osnova.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.model.UserRole;
import ua.vesa.osnova.user.service.UserRoleService;
import ua.vesa.osnova.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/userControl")
public class AdminUserControl {
    @Autowired
    private UserService userService;
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("admin/userControl/index");
        modelAndView.addObject("usersNotAuthorization", userService.getByNotAuthorization());
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public ModelAndView authorization(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/userControl");
        User user = userService.getById(id);
        user.setEnabled(true);
        userService.authorization(user);
        String msg = "Уважаемый " + user.getName() + "\n" +
                "вы добавлены в систему osmova.ml сайт локомотивного депо «Основа»." +
                "Вы будете получать новые уведомления на адрес который указали при регистрации. Если вы пользуетесь " +
                "мобильным устройством установите e-mail клиент что бы оперативно получать новые уведомления.";
        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
        return modelAndView;
    }

    @RequestMapping(value = "/ban/{id}", method = RequestMethod.GET)
    public ModelAndView ban(@PathVariable("id") int id) {
        User user = userService.getById(id);
        user.setEnabled(false);
        userService.authorization(user);
        return new ModelAndView("redirect:/admin/userControl");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        userService.remove(userService.getById(id));
        return new ModelAndView("redirect:/admin/userControl");
    }

    @RequestMapping(value = "/roleUser/{id}", method = RequestMethod.GET)
    public ModelAndView roleUser(@PathVariable("id") int id) {
        reversRole(id, AdminController.ROLE_USER);
        return new ModelAndView("redirect:/admin/userControl");
    }
    @RequestMapping(value = "/roleAdmin/{id}", method = RequestMethod.GET)
    public ModelAndView roleAdmin(@PathVariable("id") int id) {
        reversRole(id, AdminController.ROLE_ADMIN);
        return new ModelAndView("redirect:/admin/userControl");
    }

    private void reversRole(int  id, String role) {
        User user = userService.getById(id);
        user.getUserRoles().clear();
        UserRole userRole = new UserRole(user, role);
        user.getUserRoles().add(userRole);
        userService.authorization(user);
    }

}
