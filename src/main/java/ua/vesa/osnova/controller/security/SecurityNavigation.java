package ua.vesa.osnova.controller.security;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.controller.admin.AdminController;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.GregorianCalendar;

@Controller
public class SecurityNavigation {

    private boolean flagErrorPass = true;

    @Autowired
    private UserService userService;

    @Autowired
    private MailUtil mailUtil;

    private boolean flag = false;

    @RequestMapping(value = "/user-login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("flagRegistr", flag);
        flag = false;
        return modelAndView;
    }
    @RequestMapping(value = "/error-login", method = RequestMethod.GET)
    public ModelAndView invaliLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", true);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") @Valid User user, BindingResult result,
                                     HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("registration");
        if (result.hasErrors()) {
            return modelAndView;
        }

        if (!request.getParameter("passValid").equals(user.getPassword())){
            result.reject("pass not valid");
            modelAndView.addObject("flagErrorPass", flagErrorPass);
            modelAndView.addObject("messageError", "Пароли не совпадают!");
            return modelAndView;
        }

        if (user.getPassword().length() > 20){
            result.reject("pass not valid");
            modelAndView.addObject("flagErrorPass", flagErrorPass);
            modelAndView.addObject("messageError", "макс. 20 символов!");
            return modelAndView;
        }

        user.setDate_register(GregorianCalendar.getInstance().getTimeInMillis());

        userService.add(user);
        modelAndView.setViewName("redirect:/");

        String message = "User: " + user.getUsername() + "\n" +
                "Name: " + user.getName() + "\n" +
                "Surname: " + user.getSurname() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Phone: " + user.getPhone();

        mailUtil.sendMail(user.getEmail(), "osnova.depo@gmail.com", "Добавлен новый user", message);
        flag = true;

        return modelAndView;
    }

    @RequestMapping(value = "lostPassword")
    public ModelAndView lostPassword(){
        ModelAndView modelAndView = new ModelAndView("lostPassword");
        return modelAndView;
    }

    @RequestMapping(value = "lostPassword", method = RequestMethod.POST)
    public ModelAndView lostPassword(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("login");
        String email = request.getParameter("email");
        User user = userService.getByEMail(email);

        if (user == null){
            modelAndView.addObject("messageFlag", true);
            modelAndView.setViewName("lostPassword");
            return modelAndView;
        }

        String newPass = RandomStringUtils.randomAlphabetic(8);
        user.setPassword(newPass);
        userService.updatePass(user);
        System.out.println(newPass);
        String msg = "Ваш новый пароль: " + newPass;
        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
        modelAndView.addObject("lostPasswordFlag", true);
        return modelAndView;
    }
}
