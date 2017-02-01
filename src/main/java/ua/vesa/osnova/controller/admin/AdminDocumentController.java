package ua.vesa.osnova.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.document.model.Category;
import ua.vesa.osnova.document.model.Document;
import ua.vesa.osnova.document.service.CategoryService;
import ua.vesa.osnova.document.service.DocumentService;
import ua.vesa.osnova.exeption.PdfDownloadExeption;
import ua.vesa.osnova.info.TableNameApp;
import ua.vesa.osnova.info.model.InformTable;
import ua.vesa.osnova.info.service.InformTableService;
import ua.vesa.osnova.mail.MailUtil;
import ua.vesa.osnova.user.model.User;
import ua.vesa.osnova.user.service.UserService;
import ua.vesa.osnova.utils.FileOIUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.GregorianCalendar;
import java.util.UUID;

@Controller
@RequestMapping("/admin/document")
public class AdminDocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileOIUtils fileOIUtils;
    @Autowired
    private InformTableService informTableService;
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private UserService userService;

    private Category category;

    public static final String PDF_PATH = "pdf";


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/document/index");
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("documents", documentService.getAll());
        modelAndView.addObject("newCategory", new Category());
        return modelAndView;
    }

    @RequestMapping(value = "/addNewCategory", method = RequestMethod.POST)
    public ModelAndView addNewCategory(@ModelAttribute("newCategory") @Valid Category category, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/document");
        if (result.hasErrors())
            return modelAndView;
        category.setTitle(category.getTitle().toLowerCase());
        if (!category.getTitle().equals("") && category.getTitle() != null && !category.getTitle().equals(" "))
            categoryService.add(category);
        return modelAndView;
    }

    @RequestMapping(value = "/addNewDocument/{id}", method = RequestMethod.GET)
    public ModelAndView addNewDocument(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        category = categoryService.getById(id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("document", new Document());
        modelAndView.setViewName("admin/document/newDocument");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewDocument/{id}", method = RequestMethod.POST)
    public ModelAndView addNewDocument(@ModelAttribute("document") @Valid Document document,
                                       BindingResult result, HttpServletRequest request,
                                       @RequestParam(value = "file", required = false) Part file) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/document/newDocument");
            return modelAndView;
        }


        if (!(file.getSize() == 0)) {
            try {
                String uuid = UUID.randomUUID().toString() + ".pdf";
                fileOIUtils.validatePdf(file);
                document.setUuid(uuid);
                document.setTitle(document.getTitle().toLowerCase());

                document.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
                document.setCategory(category);
                documentService.add(document);
                fileOIUtils.saveDataDoc(uuid, file, PDF_PATH);
                InformTable informTable = new InformTable();
                informTable.setDate_add(GregorianCalendar.getInstance().getTimeInMillis());
                informTable.setName_table(TableNameApp.DOCUMENT);
                informTable.setTitle(document.getTitle());
                informTable.setAction("Добавлен документ " + document.getNumber_doc() + " " + document.getTitle());
                informTable.setUuid(uuid);
                informTableService.add(informTable);


                if (Boolean.valueOf(request.getParameter("sendMessage"))){
                    final String msg = "Добавлен новый документ категории " + document.getCategory().getTitle() + "\n"
                            + document.getTitle();
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

                modelAndView.setViewName("redirect:/admin/document");

            } catch (PdfDownloadExeption exeption) {
                result.reject(exeption.getMessage());
                modelAndView.setViewName("admin/document/newDocument");
                modelAndView.addObject("errorMassagePdf", "Только PDF файлы");
                modelAndView.addObject("document", document);
                modelAndView.addObject("category", category);
                return modelAndView;
            }
        } else {
            result.reject("non file");
            modelAndView.setViewName("admin/document/newDocument");
            modelAndView.addObject("errorMassagePdf", "Добавьте файл");
            modelAndView.addObject("document", document);
            modelAndView.addObject("category", category);
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/document");
        Document document = documentService.getById(id);
        fileOIUtils.deleteDataDoc(document.getUuid() + ".pdf", PDF_PATH);
        documentService.remove(document);
        InformTable informTable = informTableService.getByUUID(document.getUuid());
        if (informTable != null)
            informTableService.remove(informTable);
        return modelAndView;
    }

}
