package Builder;

public class FightClub  implements IMovie{
    Movie movie;

    public FightClub() {
        this.movie = new Movie();
    }

    @Override
    public void addTitle() {
        this.movie.setTitle("Fight Club");
    }

    public void addAgeRecommendation() {
        this.movie.setAgeRecommendation("18");
    }

    public void addSynopsis() {
        this.movie.setSynopsis("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.");
    }

    public void addDuration() {
        this.movie.setDuration("2H19");
    }

    public void addGenre() {
        this.movie.setGenre("Drama");
    }

    public void addReleaseYear() {
        this.movie.setReleaseYear(1999);
    }

    @Override
    public Movie getMovie() {
        return this.movie;
    }
}
