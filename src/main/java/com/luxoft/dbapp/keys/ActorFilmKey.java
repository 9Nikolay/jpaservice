package com.luxoft.dbapp.keys;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Data
public final class ActorFilmKey implements Serializable {
    @Column(name = "film_id")
    private Long filmId;
    @Column(name = "actor_id")
    private Long actorId;

    public ActorFilmKey(){}

    public ActorFilmKey(Long filmId, Long actorId) {
        this.filmId = filmId;
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorFilmKey that = (ActorFilmKey) o;
        return filmId.equals(that.filmId) && actorId.equals(that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, actorId);
    }
}
