package com.example.library.models;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class Notification {

    public String notificationUser(User user) {
        int maxDays = 7;
        int currentDay = LocalDateTime.now().getDayOfYear();
        Set<Book> books = user.getBooks();
        for (Book book : books) {
            int dayOfGetBook = book.getTimeGetBook().getDayOfYear();
            int differenceOfDays = currentDay - dayOfGetBook;
            int timeLeft = maxDays - differenceOfDays;
            if (differenceOfDays >= maxDays) {
                return user.getName() + ", у Вас не здана вовремя книга " + "\"" + book.getName() + "\"" + " просрочка - " + differenceOfDays + " дней!";
            }
            return user.getName() + ", Вам необходимо вернуть книгу " + "\"" + book.getName() + "\"" + " через " + timeLeft + " дней!";
        }
        return " ";
    }
}
