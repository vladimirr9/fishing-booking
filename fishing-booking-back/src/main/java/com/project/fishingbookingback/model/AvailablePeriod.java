package com.project.fishingbookingback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AvailablePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;


    @ManyToOne()
    @JoinTable(
            name = "instructor_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private FishingInstructor fishingInstructor;

    @ManyToOne()
    @JoinTable(
            name = "home_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "holiday_home_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private HolidayHome holidayHome;

    @ManyToOne()
    @JoinTable(
            name = "boat_available_periods",
            joinColumns = {@JoinColumn(name = "available_periods_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "boat_id", referencedColumnName = "id")}

    )
    @JsonBackReference
    private Boat boat;

    public AvailablePeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;

    }

    private AvailablePeriod(LocalDateTime fromTime, LocalDateTime toTime, FishingInstructor fishingInstructor, HolidayHome holidayHome, Boat boat) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.fishingInstructor = fishingInstructor;
        this.holidayHome = holidayHome;
        this.boat = boat;
    }

    public AvailablePeriod sliceBefore(LocalDateTime time) {
        if (time.equals(this.toTime)) {
            return null;
        }
        return new AvailablePeriod(this.fromTime, time, this.fishingInstructor, this.holidayHome, this.boat);
    }

    public AvailablePeriod sliceAfter(LocalDateTime time) {
        if (time.equals(this.fromTime)) {
            return null;
        }
        return new AvailablePeriod(time, this.toTime, this.fishingInstructor, this.holidayHome, this.boat);
    }

    public boolean extendWith(AvailablePeriod period) {
        if(this.fromTime.equals(period.getToTime())) {
            this.fromTime = period.fromTime;
            return true;
        }
        if(this.toTime.equals(period.getFromTime())) {
            this.toTime = period.toTime;
            return true;
        }
        return false;
    }

    public AvailablePeriod() {
    }

    public HolidayHome getHolidayHome() {return holidayHome;}
    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }
    public Boat getBoat() {return boat;}

    public Long getId() {
        return id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public boolean overlaps(LocalDateTime time){
        return !time.isBefore(fromTime) && !time.isAfter(toTime);
    }
    public boolean overlapsForNewPeriod(LocalDateTime from, LocalDateTime to) {
        var startOverlaps =  !from.isBefore(this.fromTime) && from.isBefore(this.toTime);
        var endOvelaps = !to.isAfter(this.toTime) && to.isAfter(this.fromTime);
        return startOverlaps || endOvelaps;
    }
    public boolean overlaps(AvailablePeriod availablePeriod) {
        return overlapsForNewPeriod(availablePeriod.fromTime, availablePeriod.toTime) || (availablePeriod.fromTime.isBefore(this.fromTime) && availablePeriod.toTime.isAfter(this.toTime));
    }
    public boolean overlaps(Reservation reservation) {
        return overlapsForNewPeriod(reservation.getStartDate(), reservation.getEndDate()) || (reservation.getStartDate().isBefore(this.fromTime) && reservation.getEndDate().isAfter(this.toTime));
    }
    public boolean overlaps(Promotion promotion) {
        return overlapsForNewPeriod(promotion.getFromTime(), promotion.getToTime()) || (promotion.getFromTime().isBefore(this.fromTime) && promotion.getToTime().isAfter(this.toTime));
    }
    public boolean availableForReservation(Reservation reservation) {
        return !reservation.getStartDate().isBefore(this.fromTime) && !reservation.getEndDate().isAfter(this.toTime);
    }

}
