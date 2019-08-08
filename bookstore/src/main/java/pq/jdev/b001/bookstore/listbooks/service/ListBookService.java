package pq.jdev.b001.bookstore.listbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pq.jdev.b001.bookstore.books.model.Book;


@Service
public interface ListBookService {
	Iterable<Book> findAll();

    List<Book> search(String q);

    Book findOne(long id);

    void save(Book contact);

    void delete(long id);
}