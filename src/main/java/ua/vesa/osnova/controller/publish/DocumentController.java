package ua.vesa.osnova.controller.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vesa.osnova.document.model.Document;
import ua.vesa.osnova.document.service.CategoryService;
import ua.vesa.osnova.document.service.DocumentService;
import ua.vesa.osnova.utils.FileOIUtils;
import ua.vesa.osnova.utils.Translit;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private FileOIUtils fileOIUtils;
    @Autowired
    private CategoryService categoryService;



    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("document/index");
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("docPresent", documentService.get10Items());
        return modelAndView;
    }
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("document/index");
        modelAndView.addObject("specificCategory", categoryService.getById(id));
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }


    @RequestMapping(value = "/viewDocument/{uuid}", method = RequestMethod.GET)
    public void viewDocument(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        Document document = documentService.getByUUID(uuid);
        fileOIUtils.streamReport(response, fileOIUtils.getDataDoc(uuid),
                (document.getTitle() + ".pdf"), false);
    }
}
