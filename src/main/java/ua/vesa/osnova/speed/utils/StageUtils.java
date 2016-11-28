package ua.vesa.osnova.speed.utils;

import ua.vesa.osnova.speed.stage.service.StageService;
import ua.vesa.osnova.speed.station.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StageUtils {
    private List<Station> stations;
    private StageService stageService;
    private List<List<String>> lists;

    public StageUtils(List<Station> stations, StageService stageService) {
        this.stations = stations;
        this.stageService = stageService;
    }

    private void addListsStation(){
        lists = new ArrayList<>();
        for (int i = 0;  i < stations.size() - 1; i++){
            List<String> tmpListStation = new ArrayList<>();
            tmpListStation.add(stations.get(i).getTitle_station());
            tmpListStation.add(stations.get(i + 1).getTitle_station());
            lists.add(tmpListStation);
        }
    }

    public void addStageDB(){
        addListsStation();
        stageService.addAll(lists);
    }

    public List<String> getStationStr(){
        List<String> listStrStation = new ArrayList<>();
        for (Station station : stations){
            listStrStation.add(station.getTitle_station());
        }
        return listStrStation;
    }

}
