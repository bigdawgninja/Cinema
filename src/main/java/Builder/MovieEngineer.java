package Builder;

public class MovieEngineer {
    IMovie iMovie;


    public MovieEngineer(IMovie iMovie){
        this.iMovie = iMovie;
    }

    public void createMovie(){
        this.iMovie.addTitle();
        this.iMovie.addDuration();
        this.iMovie.addAgeRecommendation();
        this.iMovie.addSynopsis();
        this.iMovie.addGenre();
        this.iMovie.addReleaseYear();
    }

    public Movie getMovie(){
        return this.iMovie.getMovie();
    }
}
