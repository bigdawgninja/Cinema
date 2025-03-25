package Builder;

public interface IMovie {
    void addTitle();
    void addAgeRecommendation();
    void addSynopsis();
    void addDuration();
    void addGenre();
    void addReleaseYear();

    Movie getMovie();
}
