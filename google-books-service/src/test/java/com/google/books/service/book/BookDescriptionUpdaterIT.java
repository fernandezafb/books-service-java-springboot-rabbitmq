package com.google.books.service.book;

import com.google.books.service.BookDescriptionUpdaterTestConfiguration;
import com.google.books.service.book.BookDescriptionUpdater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * IT for testing the Google Books API.
 *
 * @author afernandez
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BookDescriptionUpdaterTestConfiguration.class})
public class BookDescriptionUpdaterIT {

    private final String ISBN = "9780345805362";

    @Autowired
    private BookDescriptionUpdater bookDescriptionUpdater;

    @Test
    public void updateDescriptionFromIsbn() throws Exception {
        final String newDescription = bookDescriptionUpdater.updateDescriptionFromIsbn(ISBN);

        assertNotNull(newDescription);
        assertTrue(newDescription.contains("Viajé, amé, perdí, confié y me traicionaron."));
    }
}