package ua.vesa.osnova.controller.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.controller.admin.AdminController;
import ua.vesa.osnova.info.service.InformTableService;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HelloController {

    public static int countVisit ;

    @Autowired
    private InformTableService informTableService;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserService userService;



    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome() {
        countVisit++;
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("listInform", informTableService.getAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println(name);
        return modelAndView;
    }


    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public ModelAndView sendMessage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        String title = request.getParameter("titleMessage");
        String messageText = request.getParameter("textMessage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.getByName(name);
        if (!title.equals("") || !messageText.equals("")){
            String message = "user: " + user.getUsername() +  "\n"+
                    "фамилия: " + user.getSurname() + "\n" +
                    "Сообщение: " + messageText;
            mailUtil.sendMail(AdminController.E_MAIL, AdminController.E_MAIL, title, message);
        }
        return modelAndView;
    }

}
