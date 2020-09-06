package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.BookLoanDAO;
import com.smoothstack.lms.adminservice.entity.BookLoan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookLoanServiceTest {
    private static final BookLoan.BookLoanId ID1 = new BookLoan.BookLoanId(1L, 1L, 1L);
    private static final BookLoan.BookLoanId ID2 = new BookLoan.BookLoanId(2L, 2L, 2L);
    private final static LocalDate DATE_OUT1 = LocalDate.of(2020, 4, 16);
    private final static LocalDate DATE_OUT2 = LocalDate.of(2020, 3, 12);
    private final static LocalDate DUE_DATE1 = LocalDate.of(2020, 4, 23);
    private final static LocalDate DUE_DATE2 = LocalDate.of(2020, 3, 19);
    private final static LocalDate DATE_IN1 = null;
    private final static LocalDate DATE_IN2 = LocalDate.of(2020, 3, 18);

    @Mock
    private BookLoanDAO bookLoanDAO;

    @InjectMocks
    private BookLoanService bookLoanService;

    @Test
    public void testGetBookLoan() {
        // given
        BookLoan bookLoan = new BookLoan(ID1, DATE_OUT1, DUE_DATE1, DATE_IN1);
        when(bookLoanDAO.findById(ID1)).thenReturn(Optional.of(bookLoan));

        // when
        BookLoan returned = bookLoanService.getBookLoan(ID1);

        // then
        verify(bookLoanDAO, times(1)).findById(ID1);
        verifyNoMoreInteractions(bookLoanDAO);
        assertEquals(bookLoan, returned);
    }

    @Test
    public void testGetBookLoans() {
        // given
        BookLoan bookLoan1 = new BookLoan(ID1, DATE_OUT1, DUE_DATE1, DATE_IN1);
        BookLoan bookLoan2 = new BookLoan(ID2, DATE_OUT2, DUE_DATE2, DATE_IN2);
        List<BookLoan> bookLoans = Arrays.asList(bookLoan1, bookLoan2);
        when(bookLoanDAO.findAll()).thenReturn(bookLoans);

        // when
        List<BookLoan> returned = bookLoanService.getBookLoans();

        // then
        verify(bookLoanDAO, times(1)).findAll();
        verifyNoMoreInteractions(bookLoanDAO);
        assertEquals(bookLoans, returned);
    }

    @Test
    public void testSetBookLoan() {
        // given
        BookLoan bookLoan = new BookLoan(ID1, DATE_OUT1, DUE_DATE1, DATE_IN1);
        when(bookLoanDAO.save(bookLoan)).thenReturn(bookLoan);

        // when
        BookLoan returned = bookLoanService.setBookLoan(bookLoan);

        // then
        ArgumentCaptor<BookLoan> bookLoanArgument = ArgumentCaptor.forClass(BookLoan.class);
        verify(bookLoanDAO, times(1)).save(bookLoanArgument.capture());
        verifyNoMoreInteractions(bookLoanDAO);
        assertBookLoan(bookLoan, bookLoanArgument.getValue());
        assertEquals(bookLoan, returned);
    }

    @Test
    public void testDeleteBookLoan() {
        // given
        BookLoan bookLoan = new BookLoan(ID1, DATE_OUT1, DUE_DATE1, DATE_IN1);
        when(bookLoanDAO.findById(bookLoan.getId())).thenReturn(Optional.of(bookLoan));

        // when
        bookLoanService.deleteBookLoan(bookLoan.getId());

        // then
        verify(bookLoanDAO, times(1)).findById(bookLoan.getId());
        verify(bookLoanDAO, times(1)).delete(bookLoan);
        verifyNoMoreInteractions(bookLoanDAO);
    }

    private void assertBookLoan(BookLoan expected, BookLoan actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDueDate(), actual.getDueDate());
        assertEquals(expected.getDateOut(), actual.getDateOut());
        assertEquals(expected.getDateIn(), actual.getDateIn());
    }
}
