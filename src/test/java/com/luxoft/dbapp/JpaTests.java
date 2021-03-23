package com.luxoft.dbapp;

import com.luxoft.dbapp.dao.ActorDao;
import com.luxoft.dbapp.dao.FilmDao;
import com.luxoft.dbapp.dao.LanguageDao;
import com.luxoft.dbapp.entities.Actor;
import com.luxoft.dbapp.entities.Film;
import com.luxoft.dbapp.entities.FilmActor;
import com.luxoft.dbapp.entities.Language;
import com.luxoft.dbapp.enums.Rating;
import com.luxoft.dbapp.keys.ActorFilmKey;
import com.luxoft.dbapp.services.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

class JpaTests {

	private static AnnotationConfigApplicationContext ctx;
	private static FilmService filmService;
	private static LanguageDao languageDao;
	private static FilmDao filmDao;
	private static ActorDao actorDao;

	private static final String TITLE = "ACADEMY DINOSAUR";

	@BeforeAll
	public static void init(){
		ctx = new AnnotationConfigApplicationContext(DbConfigTest.class);
		filmService = ctx.getBean(FilmService.class);
		languageDao = ctx.getBean(LanguageDao.class);
		filmDao = ctx.getBean(FilmDao.class);
		actorDao = ctx.getBean(ActorDao.class);
		saveActors();
	}

	private static void saveActors(){
		try(Stream<String> inputStream = Files.lines(
				Paths.get("src/test/resources/db/sakila-data.sql")
		)){
			inputStream
					.dropWhile(str -> !str.startsWith("INSERT INTO actor VALUES"))
					.takeWhile(str -> !str.startsWith("COMMIT"))
					.map(str -> str.substring(str.indexOf("(") + 1, str.indexOf(")")))
					.peek(str -> {
						String[] arr = str.split(",");
						String date = arr[3].substring(1, arr[3].length() - 1);
						String firstName = arr[1].substring(1, arr[1].length() - 1);
						String lastName = arr[2].substring(1, arr[2].length() - 1);
						actorDao.save(new Actor(Long.valueOf(arr[0]), firstName, lastName, LocalDateTime.parse(
								date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
						)));
					})
					.forEach(System.out::println);
		}
		catch (IOException e){
			Assertions.fail(e.getCause());
		}
	}

	@Test
	public void test(){
		Assertions.assertEquals(200, actorDao.count());

		Language language = new Language();
		language.setLanguageId(1L);
		language.setName("French");
		language.setLastUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		languageDao.save(language);
		Assertions.assertEquals(1, languageDao.count());

		Film film = new Film();
		film.setFilmId(9999L);
		film.setTitle(TITLE);
		film.setDescription("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies");
		film.setReleaseYear(2006);
		film.setLanguageId(language);
		film.setOriginalLanguage_id(language);
		film.setRentalDuration((byte)1);
		film.setRentalRate(new BigDecimal("1.00"));
		film.setLength((short)1);
		film.setReplacementCost(new BigDecimal("1.00"));
		film.setRating(Rating.G);
		film.setSpecialFeatures("Deleted Scenes,Behind the Scenes");
		film.setLastUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		filmService.saveFilm(film);
		List<Film> savedFilms = filmDao.findByTitle(TITLE);
		Assertions.assertEquals(1, savedFilms.size());
		Assertions.assertEquals(film, savedFilms.get(0));

		Actor actor = new Actor();
		actor.setActorIid(9999L);
		actor.setFirstName("JENNIFER");
		actor.setLastName("DAVIS");
		actor.setLastUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

		FilmActor filmActor = new FilmActor();
		ActorFilmKey actorFilmKey = new ActorFilmKey();
		actorFilmKey.setFilmId(film.getFilmId());
		actorFilmKey.setActorId(actor.getActorIid());
		filmActor.setActorFilmKey(actorFilmKey);
		filmActor.setCategory(1L);
		filmActor.setLastUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		filmService.saveFilmActor(filmActor);

		List<String> descriptions = filmService.findAllByNativeQuery();

		Assertions.assertEquals(1, descriptions.size());
	}
}
