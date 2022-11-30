package hellojpa;

import hellojpa.entity.item.Item;
import hellojpa.entity.item.Movie;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {

            Movie movie = new Movie();
            movie.setName("바람과함께 사라지다");
            movie.setPrice(10000);
            movie.setDirector("movie director1");
            movie.setActor("movie actor1");

            em.persist(movie);

            em.flush(); //DB 재조회를 위해 1차캐시 제거
            em.clear();

            Item findMovie = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + findMovie.getName());
            System.out.println("findMovie = " + findMovie.getPrice());
        });
    }
}
