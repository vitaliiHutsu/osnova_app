package ua.vesa.osnova.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.info.TableNameApp;
import ua.vesa.osnova.info.model.InformTable;
import ua.vesa.osnova.info.service.InformTableService;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;

import java.util.GregorianCalendar;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public static final String E_MAIL = "osnova.depo@gmail.com";
    public static final String TITLE = "Локомотивное депо «Основа»";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private InformTableService informTableService;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printWelcome(){
        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("listInformation", informTableService.getAll());
        modelAndView.addObject("newInform", new InformTable());
        return modelAndView;
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.POST)
    public ModelAndView newMessage(@ModelAttribute("newInform") InformTable informTable){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        if (informTable.getAction() != "" && informTable.getAction() != null){
            informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
            informTable.setTitle("Сообщение");
            informTable.setName_table(TableNameApp.INFORMATION_TABLE);
            informTable.setUuid(UUID.randomUUID().toString());
            informTableService.add(informTable);
            final String msg = "Новое сообщение:\n" + informTable.getAction();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : userService.getAll()){
                        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
                    }
                }
            });
            thread.start();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteInformation/{id}")
    public ModelAndView deleteInformation(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        informTableService.remove(informTableService.getById(id));

        return modelAndView;
    }

}
