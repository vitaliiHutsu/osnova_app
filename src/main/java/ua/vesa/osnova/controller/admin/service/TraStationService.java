package ua.vesa.osnova.controller.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;
import ua.vesa.osnova.speed.TRAStation.service.TRAService;
import ua.vesa.osnova.speed.station.model.Station;
import ua.vesa.osnova.speed.station.service.StationService;
import ua.vesa.osnova.utils.FileOIUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.*;

@RestController
@RequestMapping("/admin/service/traStation")
public class TraStationService {
    @Autowired
    private FileOIUtils fileOIUtils;
    @Autowired
    private TRAService traService;
    public static final String IMG_STATION_PATH = "img_station_path";


    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String addImgStation(@RequestParam(value = "fileIMG", required = false) Part fileIMG,
                         HttpServletRequest req) {
        if (fileIMG.getContentType().equals("image/jpeg") || fileIMG.getContentType().equals("image/png")) {

            String uuid = UUID.randomUUID().toString() + ".jpg";
            TRAModel traModelDel = traService.getTitleByIdStation(Integer.valueOf(req.getParameter("idStation")));
            if (traModelDel != null) {
                fileOIUtils.deleteDataDoc(traModelDel.getTitle(), IMG_STATION_PATH);
                traService.update(traModelDel);
                fileOIUtils.saveDataDoc(traModelDel.getTitle(), fileIMG, IMG_STATION_PATH);
            }else {
                fileOIUtils.saveDataDoc(uuid, fileIMG, IMG_STATION_PATH);
                TRAModel traModel = new TRAModel();
                traModel.setId_station(Integer.valueOf(req.getParameter("idStation")));
                traModel.setTitle(uuid);
                traModel.setData(new Date().getTime());
                traService.add(traModel);
            }
        } else {
            return "error";
        }
        return "ok";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    TRAModel get(@PathVariable int id) {
        return traService.getTitleByIdStation(id);
    }

}
