package ua.vesa.osnova.threads;

import ua.vesa.osnova.controller.admin.AdminController;
import ua.vesa.osnova.info.TableNameApp;
import ua.vesa.osnova.info.model.InformTable;
import ua.vesa.osnova.info.service.InformTableService;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.schedule.model.Schedule;
import ua.vesa.osnova.schedule.service.ScheduleService;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;

import java.util.Date;
import java.util.GregorianCalendar;

public class ScheduleEditThread implements Runnable{
    private Schedule schedule;
    private ScheduleService sessionFactory;
    private Date date;


    private InformTableService informTableService;

    private UserService userService;

    private MailUtil mailUtil;

    private boolean sendUser;

    public ScheduleEditThread(Schedule schedule, ScheduleService sessionFactory, Date date){
        this.schedule = schedule;
        this.sessionFactory = sessionFactory;
        this.date = date;
    }

    public void setSendUser(boolean sendUser) {
        this.sendUser = sendUser;
    }

    public void setInformTableService(InformTableService informTableService) {
        this.informTableService = informTableService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Override
    public void run() {
        if (date != null){
            while (true){
                if (new Date().getTime() > date.getTime()) {
                    break;
                }

                try {
                    Thread.sleep(1000 * 60 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        InformTable informTable = informTableService.getByTitle((String.valueOf(schedule.getId())));
        if (informTable != null)
            informTableService.remove(informTable);
        informTable = new InformTable();
        final String msg = "изменения в расписании п. № " + schedule.getNumber_train();
        informTable.setAction(msg);
        informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
        informTable.setTitle(String.valueOf(schedule.getId()));
        informTable.setName_table(TableNameApp.SCHEDULE_TABLE);
        informTableService.add(informTable);

        if(sendUser) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (User user : userService.getAll()) {
                        mailUtil.sendMail(AdminController.E_MAIL, user.getEmail(), AdminController.TITLE, msg);
                    }
                }
            });
            thread.start();
        }
        sessionFactory.update(schedule);
    }

}
