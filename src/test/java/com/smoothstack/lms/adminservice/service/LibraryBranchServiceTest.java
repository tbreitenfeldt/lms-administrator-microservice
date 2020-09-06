package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.LibraryBranchDAO;
import com.smoothstack.lms.adminservice.entity.LibraryBranch;
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
public class LibraryBranchServiceTest {
    private final static long ID1 = 1L;
    private final static long ID2 = 2L;
    private final static String NAME1 = "Library of Congress";
    private final static String NAME2 = "Library of Alexandria";
    private final static String ADDRESS1 = "101 Independence Ave SE, Washington, DC 20540";
    private final static String ADDRESS2 = "Alexandria, Ancient Egypt";

    @Mock
    private LibraryBranchDAO libraryBranchDAO;

    @InjectMocks
    private LibraryBranchService libraryBranchService;

    @Test
    public void testGetLibraryBranch() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(ID1, NAME1, ADDRESS1);
        when(libraryBranchDAO.findById(ID1)).thenReturn(Optional.of(libraryBranch));

        // when
        LibraryBranch returned = libraryBranchService.getLibraryBranch(ID1);

        // then
        verify(libraryBranchDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(libraryBranchDAO);
        assertEquals(libraryBranch, returned);
    }

    @Test
    public void testGetLibraryBranches() {
        // given
        LibraryBranch libraryBranch1 = new LibraryBranch(ID1, NAME1, ADDRESS1);
        LibraryBranch libraryBranch2 = new LibraryBranch(ID2, NAME2, ADDRESS2);
        List<LibraryBranch> libraryBranches = Arrays.asList(libraryBranch1, libraryBranch2);
        when(libraryBranchDAO.findAll()).thenReturn(libraryBranches);

        // when
        List<LibraryBranch> returned = libraryBranchService.getLibraryBranches();

        // then
        verify(libraryBranchDAO, times(1)).findAll();
        verifyNoMoreInteractions(libraryBranchDAO);
        assertEquals(libraryBranches, returned);
    }

    @Test
    public void testSetLibraryBranchWithNewLibraryBranch() {
        // given
        LibraryBranch created = new LibraryBranch(null, NAME1, ADDRESS1);
        LibraryBranch persisted = new LibraryBranch(ID1, NAME1, ADDRESS1);
        when(libraryBranchDAO.save(created)).thenReturn(persisted);

        // when
        LibraryBranch returned = libraryBranchService.setLibraryBranch(created);

        // then
        ArgumentCaptor<LibraryBranch> libraryBranchArgument = ArgumentCaptor.forClass(LibraryBranch.class);
        verify(libraryBranchDAO, times(1)).save(libraryBranchArgument.capture());
        verifyNoMoreInteractions(libraryBranchDAO);
        assertLibraryBranch(created, libraryBranchArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetLibraryBranchWithExistingLibraryBranch() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(ID1, NAME1, ADDRESS1);
        when(libraryBranchDAO.save(libraryBranch)).thenReturn(libraryBranch);

        // when
        LibraryBranch returned = libraryBranchService.setLibraryBranch(libraryBranch);

        // then
        ArgumentCaptor<LibraryBranch> libraryBranchArgument = ArgumentCaptor.forClass(LibraryBranch.class);
        verify(libraryBranchDAO, times(1)).save(libraryBranchArgument.capture());
        verifyNoMoreInteractions(libraryBranchDAO);
        assertLibraryBranch(libraryBranch, libraryBranchArgument.getValue());
        assertEquals(libraryBranch, returned);
    }

    @Test
    public void testDeleteLibraryBranch() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(ID1, NAME1, ADDRESS1);
        when(libraryBranchDAO.findById(libraryBranch.getId())).thenReturn(Optional.of(libraryBranch));

        // when
        libraryBranchService.deleteLibraryBranch(libraryBranch.getId());

        // then
        verify(libraryBranchDAO, times(1)).findById(libraryBranch.getId());
        verify(libraryBranchDAO, times(1)).delete(libraryBranch);
        verifyNoMoreInteractions(libraryBranchDAO);
    }

    private void assertLibraryBranch(LibraryBranch expected, LibraryBranch actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(), actual.getAddress());
    }
}
