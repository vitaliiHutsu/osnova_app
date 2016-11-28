package ua.vesa.osnova.schedule.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.vesa.osnova.speed.station.model.Station;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route_schedule")
public class RouteSchedule implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 6, message = "мин. 6 символов")
    @Column(nullable = false, unique = true)
    private String name_route;
    @Column(columnDefinition = "TEXT")
    private String notation;
    private Long date;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany()
    @JoinTable(name = "route_schedule_station", joinColumns = @JoinColumn(name = "route_station_id"),
    inverseJoinColumns = @JoinColumn(name = "station_route_id"))
    @JsonManagedReference
    private List<Station> stations = new ArrayList<>();

    public RouteSchedule(int id) {
        this.id = id;
    }

    public RouteSchedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_route() {
        return name_route;
    }

    public void setName_route(String name_route) {
        this.name_route = name_route;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public Long getDate() {
        return date;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    //
//    public List<Station> getStations() {
//        return stations;
//    }
//
//    public void setStations(List<Station> stations) {
//        this.stations = stations;
//    }
}
