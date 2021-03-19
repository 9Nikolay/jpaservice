package com.luxoft.dbapp.keys;

import java.io.Serializable;
import java.util.Objects;

public final class FilmCategoryKey implements Serializable {
    private Short film;
    private Byte category;

    public FilmCategoryKey(){}

    public FilmCategoryKey(Short film, Byte category) {
        this.film = film;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryKey that = (FilmCategoryKey) o;
        return film.equals(that.film) && category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, category);
    }
}
