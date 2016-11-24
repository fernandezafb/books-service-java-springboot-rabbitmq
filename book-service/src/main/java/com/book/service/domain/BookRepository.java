package com.book.service.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Repository to expose as a REST resource.
 *
 * @author afernandez
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    @RestResource(path = "description")
    List<Book> findByDescriptionContaining(@Param("text") String note);
}
