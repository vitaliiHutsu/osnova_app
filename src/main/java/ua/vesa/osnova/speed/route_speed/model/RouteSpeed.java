package ua.vesa.osnova.speed.route_speed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import ua.vesa.osnova.speed.route_speed.valid.RouteSpeedValid;
import ua.vesa.osnova.speed.station.model.Station;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route_speed")
public class RouteSpeed implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @RouteSpeedValid
    @NotEmpty
    @Size(min = 4)
    private String title_route_speed;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany()
//    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "route_speed_station", joinColumns = @JoinColumn(name = "route_speed_station_id"),
            inverseJoinColumns = @JoinColumn(name = "station_route_speed_id"))
    private List<Station> stations = new ArrayList<>();
    @Column(columnDefinition = "TEXT")
    private String notation;
    private Long date;

    public RouteSpeed(int id) {
        this.id = id;
    }

    public RouteSpeed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_route_speed() {
        return title_route_speed;
    }

    public void setTitle_route_speed(String title_route_speed) {
        this.title_route_speed = title_route_speed;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
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

    public void setDate(Long date) {
        this.date = date;
    }

}
