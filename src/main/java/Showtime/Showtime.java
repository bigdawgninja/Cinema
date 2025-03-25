package Showtime;

import java.time.LocalDateTime;

public class Showtime {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int hall;

    public Showtime(LocalDateTime startTime, LocalDateTime endTime, int hall){
        this.startTime =startTime;
        this.endTime = endTime;
        this.hall = hall;
    }
    public LocalDateTime getStartTime(){
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime){
        this.startTime=startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCinemaHall() {
        return hall;
    }

    public void setCinemaHall(int hall) {
        this.hall = hall;
    }


}
