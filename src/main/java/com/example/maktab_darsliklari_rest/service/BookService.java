package com.example.maktab_darsliklari_rest.service;

import com.example.maktab_darsliklari_rest.dto.ApiResponse;
import com.example.maktab_darsliklari_rest.dto.BookDTO;
import com.example.maktab_darsliklari_rest.entity.Book;
import com.example.maktab_darsliklari_rest.entity.Cart;
import com.example.maktab_darsliklari_rest.repository.BookRepository;
import com.example.maktab_darsliklari_rest.repository.CartRepository;
import com.example.maktab_darsliklari_rest.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {
    final BookRepository bookRepository;
    final ClassRepository classRepository;
    final CartRepository cartRepository;

    public ApiResponse getAll() {
        List<Book> all = bookRepository.findAll();
        return new ApiResponse("List of books", true, all);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik", false);
        return new ApiResponse("Mana", true, byId.get());
    }

    public ApiResponse save(BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setAuthors(dto.getAuthors());
        book.setClasses(classRepository.getById(dto.getClassId()));
        Book save = bookRepository.save(book);
        return new ApiResponse("Created", true, save);
    }

    public ApiResponse edit(Integer id, BookDTO dto) {
        Book book = bookRepository.getById(id);
        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setAuthors(dto.getAuthors());
        book.setClasses(classRepository.getById(dto.getClassId()));
        Book save = bookRepository.save(book);
        return new ApiResponse("Edited", true, save);
    }

    public ApiResponse delete(Integer id) {
        bookRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse searchBy(Integer classId, Integer langId) {
        List<Book> books;
        if (classId == 0 && langId == 0) {
            books = bookRepository.findAll();
        } else if (classId == 0) { //tilni tanladi
            books = bookRepository.findAllByLanguage_Id(langId);
        } else if (langId == 0) {
            books = bookRepository.findAllByClasses_Id(classId);
        } else {
            books = bookRepository.findAllByClasses_IdAndLanguage_Id(classId, langId);
        }
        return new ApiResponse("Mana", true, books);
    }

    public ApiResponse addCart(String deviceId) {
        Optional<Cart> byDeviceId = cartRepository.findByDeviceId(deviceId);
        if (byDeviceId.isEmpty()) {
            Cart cart = new Cart();
            cart.setDeviceId(deviceId);
            Cart save = cartRepository.save(cart);
            return new ApiResponse("Cart yaratildi", true, save);
        } else {
            Cart cart = byDeviceId.get();
            return new ApiResponse("Bor cart", true, cart);
        }
    }

    public ApiResponse saved(String deviceId, Integer bookId) {

        //2-variant
        boolean isHave = false;

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.get();
        List<Book> newBooks = new ArrayList<>();
        Optional<Cart> optionalCart = cartRepository.findByDeviceId(deviceId);
        Cart cart = optionalCart.get();

        if (cartRepository.existsByBooks(book)) {
            isHave = true;
        }
        if (isHave) {
            List<Book> books = cart.getBooks();
            for (Book book1 : books) {
                if (!book1.equals(book)) {
                    newBooks.add(book1);
                }
            }
            cart.setBooks(newBooks);
        } else {
            List<Book> books = cart.getBooks();
            books.add(book);
            cart.setBooks(books);
        }
        Cart save = cartRepository.save(cart);
        return new ApiResponse("Qoshildi", true, save);
        /**
         *  agar shunaqa
         */
//        if (!saved.add(book)) {
//            saved.remove(book);
//            cartRepository.save(cart);
//            return new ApiResponse("Favorite", true);
//        }
//        cart.setSaved(saved);
//        cartRepository.save(cart);
//        return new ApiResponse("Favorite", true);
    }
}
