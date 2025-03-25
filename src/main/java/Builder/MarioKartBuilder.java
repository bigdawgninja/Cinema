package Builder;

public class MarioKartBuilder implements IMovie{
    Movie movie;

    public MarioKartBuilder(){
        this.movie = new Movie();
    }

    @Override
    public void addTitle() {
        this.movie.setTitle("Mario Kart the revenge");
    }
    public void addAgeRecommendation(){
        this.movie.setAgeRecommendation("6");
    }
    public void addSynopsis(){
        this.movie.setSynopsis("Mario Kart-themed movie with action-packed racing scenes.");
    }
    public void addDuration(){
        this.movie.setDuration("1H20");
    }
    public void addGenre(){
        this.movie.setGenre("Family Friendly");
    }
    public void addReleaseYear(){
        this.movie.setReleaseYear(2023);
    }

    @Override
    public Movie getMovie() {
        return this.movie;
    }
}
