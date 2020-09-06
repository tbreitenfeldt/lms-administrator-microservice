package com.smoothstack.lms.adminservice.service;


import com.smoothstack.lms.adminservice.dao.BorrowerDAO;
import com.smoothstack.lms.adminservice.entity.Borrower;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowerServiceTest {
    @Mock
    private BorrowerDAO borrowerDAO;

    @InjectMocks
    private BorrowerService borrowerService;

    @Test
    public void testGetBorrower() {
        // given
        Borrower borrower = new Borrower(1L, "Fred Flinstone","The Town of Bedrock",  "BED-ROCK");
        when(borrowerDAO.findById(1L)).thenReturn(Optional.of(borrower));

        // when
        Borrower returned = borrowerService.getBorrower(1L);

        // then
        verify(borrowerDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(borrowerDAO);
        assertEquals(borrower, returned);
    }

    @Test
    public void testGetBorrowers() {
        // given
        Borrower borrower1 = new Borrower(1L, "Fred Flinstone","The Town of Bedrock",  "BED-ROCK");
        Borrower borrower2 = new Borrower(1L, "Barnie Rubble","The Town of Bedrock",  "555-RUBL");
        List<Borrower> borrowers = Arrays.asList(borrower1, borrower2);
        when(borrowerDAO.findAll()).thenReturn(borrowers);

        // when
        List<Borrower> returned = borrowerService.getBorrowers();

        // then
        verify(borrowerDAO, times(1)).findAll();
        verifyNoMoreInteractions(borrowerDAO);
        assertEquals(borrowers, returned);
    }

    @Test
    public void testSetBorrowerWithNewBorrower() {
        // given
        Borrower created = new Borrower(null, "Fred Flinstone","The Town of Bedrock",  "BED-ROCK");
        Borrower persisted = new Borrower(1L, "Fred Flinstone","The Town of Bedrock",  "BED-ROCK");
        when(borrowerDAO.save(created)).thenReturn(persisted);

        // when
        Borrower returned = borrowerService.setBorrower(created);

        // then
        ArgumentCaptor<Borrower> borrowerArgument = ArgumentCaptor.forClass(Borrower.class);
        verify(borrowerDAO, times(1)).save(borrowerArgument.capture());
        verifyNoMoreInteractions(borrowerDAO);
        assertBorrower(created, borrowerArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetBorrowerWithExistingBorrower() {
        // given
        Borrower updated = new Borrower(1L, "Fled Frinstone","The Town of Bredlock",  "555-555");
        when(borrowerDAO.save(updated)).thenReturn(updated);

        // when
        Borrower returned = borrowerService.setBorrower(updated);

        // then
        ArgumentCaptor<Borrower> borrowerArgument = ArgumentCaptor.forClass(Borrower.class);
        verify(borrowerDAO, times(1)).save(borrowerArgument.capture());
        verifyNoMoreInteractions(borrowerDAO);
        assertBorrower(updated, borrowerArgument.getValue());
        assertEquals(updated, returned);
    }

    @Test
    public void testDeleteBorrower() {
        // given
        Borrower deleted = new Borrower(1L, "Fred Flinstone","The Town of Bedrock",  "BED-ROCK");
        when(borrowerDAO.findById(deleted.getId())).thenReturn(Optional.of(deleted));

        // when
        borrowerService.deleteBorrower(deleted.getId());

        // then
        verify(borrowerDAO, times(1)).findById(deleted.getId());
        verify(borrowerDAO, times(1)).delete(deleted);
        verifyNoMoreInteractions(borrowerDAO);
    }

    private void assertBorrower(Borrower expected, Borrower actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
