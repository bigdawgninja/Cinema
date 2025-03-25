package Builder;

public class DjangoUnchainBuilder implements IMovie{
    Movie movie;

    public DjangoUnchainBuilder() {
        this.movie = new Movie();
    }

    @Override
    public void addTitle() {
        this.movie.setTitle("Django Unchained");
    }

    public void addAgeRecommendation() {
        this.movie.setAgeRecommendation("16");
    }

    public void addSynopsis() {
        this.movie.setSynopsis("With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.");
    }

    public void addDuration() {
        this.movie.setDuration("2H45");
    }

    public void addGenre() {
        this.movie.setGenre("Drama, Western");
    }

    public void addReleaseYear() {
        this.movie.setReleaseYear(2012);
    }

    @Override
    public Movie getMovie() {
        return this.movie;
    }
}
