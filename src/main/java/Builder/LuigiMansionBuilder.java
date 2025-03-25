package Builder;

public class LuigiMansionBuilder implements IMovie {
    Movie movie;

    public LuigiMansionBuilder() {
        this.movie = new Movie();
    }

    @Override
    public void addTitle() {
        this.movie.setTitle("Luigi's Mansion: Ghostly Adventures");
    }

    public void addAgeRecommendation() {
        this.movie.setAgeRecommendation("8");
    }

    public void addSynopsis() {
        this.movie.setSynopsis("A thrilling adventure where Luigi explores haunted mansions to rescue his brother Mario.");
    }

    public void addDuration() {
        this.movie.setDuration("1H35");
    }

    public void addGenre() {
        this.movie.setGenre("Adventure, Comedy");
    }

    public void addReleaseYear() {
        this.movie.setReleaseYear(2024);
    }

    @Override
    public Movie getMovie() {
        return this.movie;
    }
}