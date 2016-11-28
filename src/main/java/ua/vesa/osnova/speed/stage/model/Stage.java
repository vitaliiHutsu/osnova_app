package ua.vesa.osnova.speed.stage.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stage_speed")
public class Stage implements Serializable{

    //Хранения title в таблице inform stage по уникальному ключу uniq_station

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(nullable = false, length = 30)
    private String startStation;
    @Column(nullable = false, length = 30)
    private String endStation;

    @Column(name = "notation_odd", length = 500)
    private String notation_odd;
    @Column(name = "notation_even", length = 500)
    private String notation_even;

    @Column(unique = true)
    private Long stage_uniq;

    private Long date;

    @Column(nullable = false, columnDefinition = "int default 80")
    private Integer speed_freight_even;
    @Column(nullable = false, columnDefinition = "int default 80")
    private Integer speed_freight_odd;
    @Column(nullable = false, columnDefinition = "int default 100")
    private Integer speed_pass_even;
    @Column(nullable = false, columnDefinition = "int default 100")
    private Integer speed_pass_odd;

    //----------Проба тормозов на перегоне------------------



    //---------------------------------------------

    public Stage(int id) {
        this.id = id;
    }

    public Stage(String startStation, String endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Stage() {
    }

    public Long getStage_uniq() {
        return stage_uniq;
    }

    public void setStage_uniq(Long stage_uniq) {
        this.stage_uniq = stage_uniq;
    }

    public String getNotation_even() {
        return notation_even;
    }

    public void setNotation_even(String notation_even) {
        this.notation_even = notation_even;
    }

    public String getNotation_odd() {
        return notation_odd;
    }

    public void setNotation_odd(String notation_odd) {
        this.notation_odd = notation_odd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getSpeed_freight_even() {
        return speed_freight_even;
    }

    public void setSpeed_freight_even(int speed_freight_even) {
        this.speed_freight_even = speed_freight_even;
    }

    public int getSpeed_freight_odd() {
        return speed_freight_odd;
    }

    public void setSpeed_freight_odd(int speed_freight_odd) {
        this.speed_freight_odd = speed_freight_odd;
    }

    public int getSpeed_pass_even() {
        return speed_pass_even;
    }

    public void setSpeed_pass_even(int speed_pass_even) {
        this.speed_pass_even = speed_pass_even;
    }

    public int getSpeed_pass_odd() {
        return speed_pass_odd;
    }

    public void setSpeed_pass_odd(int speed_pass_odd) {
        this.speed_pass_odd = speed_pass_odd;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }


}
