package com.smoothstack.lms.adminservice.controller;


import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.entity.BookLoan;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.entity.Publisher;
import com.smoothstack.lms.adminservice.service.BookLoanService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class BookLoanControllerTest {
    @InjectMocks
    private BookLoanController bookLoanController;

    @Mock
    BookLoanService bookLoanService;

    @Test
    public void testCreateBookLoan() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookLoanService.setBookLoan(any(BookLoan.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        BookLoan bookLoan = new BookLoan(new BookLoan.BookLoanId(1L, 1L, 1L), LocalDate.of(2020, 4, 16), LocalDate.of(2020, 4, 23), null);
        ResponseEntity<BookLoan> result = bookLoanController.createBookLoan(bookLoan);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getDueDate()).isEqualTo(bookLoan.getDueDate());
    }

    @Test
    public void testReadBookLoans()
    {
        // given
        LocalDate bookLoan1DateOut = LocalDate.of(2020, 4, 16);
        LocalDate bookLoan1DueDate= LocalDate.of(2020, 4, 23);
        BookLoan bookLoan1 = new BookLoan(new BookLoan.BookLoanId(1L, 1L, 1L), bookLoan1DateOut, bookLoan1DueDate, null);
        LocalDate bookLoan2DateOut = LocalDate.of(2020, 4, 4);
        LocalDate bookLoan2DueDate = LocalDate.of(2020, 4, 11);
        LocalDate bookLoan2DateIn = LocalDate.of(2020, 4, 10);
        BookLoan bookLoan2 = new BookLoan(new BookLoan.BookLoanId(2L, 2L, 2L), bookLoan2DateOut, bookLoan2DueDate, bookLoan2DateIn);
        List<BookLoan> bookLoans = Arrays.asList(bookLoan1, bookLoan2);

        when(bookLoanService.getBookLoans()).thenReturn(bookLoans);

        // when
        ResponseEntity<List<BookLoan>> result = bookLoanController.readBookLoans();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getDateOut()).isEqualTo(bookLoan1.getDateOut());
        assertThat(result.getBody().get(0).getId().getBook().getId()).isEqualTo(bookLoan1.getId().getBook().getId());
        assertThat(result.getBody().get(1).getDueDate()).isEqualTo(bookLoan2.getDueDate());
        assertThat(result.getBody().get(1).getId().getBranch().getId()).isEqualTo(bookLoan2.getId().getBranch().getId());
    }

    @Test
    public void testReadBookLoanById() {
        // given
        LocalDate bookLoanDateOut = LocalDate.of(2020, 4, 16);
        LocalDate bookLoanDueDate= LocalDate.of(2020, 4, 23);
        BookLoan bookLoan = new BookLoan(new BookLoan.BookLoanId(1L, 1L, 1L), bookLoanDateOut, bookLoanDueDate, null);
        when(bookLoanService.getBookLoan(bookLoan.getId())).thenReturn(bookLoan);

        // when
        ResponseEntity<BookLoan> result = bookLoanController.readBookLoanById(bookLoan.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getDueDate()).isEqualTo(bookLoan.getDueDate());
    }

    @Test
    public void testUpdateBookLoan() {
        // given
        LocalDate bookLoanDateOut = LocalDate.of(2020, 4, 16);
        LocalDate bookLoanDueDate= LocalDate.of(2020, 4, 23);
        BookLoan bookLoan = new BookLoan(new BookLoan.BookLoanId(1L, 1L, 1L), bookLoanDateOut, bookLoanDueDate, null);
        when(bookLoanService.setBookLoan(any(BookLoan.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<BookLoan> result = bookLoanController.updateBookLoan(bookLoan);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getDueDate()).isEqualTo(bookLoan.getDueDate());
    }

    @Test
    public void testDeleteBookLoan() {
        // given
        LocalDate bookLoanDateOut = LocalDate.of(2020, 4, 16);
        LocalDate bookLoanDueDate= LocalDate.of(2020, 4, 23);
        BookLoan bookLoan = new BookLoan(new BookLoan.BookLoanId(1L, 1L, 1L), bookLoanDateOut, bookLoanDueDate, null);
        doNothing().when(bookLoanService).deleteBookLoan(isA(BookLoan.BookLoanId.class));

        // when
        ResponseEntity<BookLoan> result = bookLoanController.deleteBookLoan(bookLoan.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
