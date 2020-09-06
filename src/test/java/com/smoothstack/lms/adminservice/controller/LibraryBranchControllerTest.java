package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.LibraryBranch;
import com.smoothstack.lms.adminservice.service.LibraryBranchService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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
public class LibraryBranchControllerTest {
    @InjectMocks
    private LibraryBranchController libraryBranchController;

    @Mock
    LibraryBranchService libraryBranchService;

    @Test
    public void testCreateLibraryBranch() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(libraryBranchService.setLibraryBranch(any(LibraryBranch.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        LibraryBranch libraryBranch = new LibraryBranch(1L, "Library of Congress", "101 Independence Ave SE, Washington, DC 20540");
        ResponseEntity<LibraryBranch> result = libraryBranchController.createLibraryBranch(libraryBranch);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(libraryBranch.getName());
        assertThat(result.getBody().getAddress()).isEqualTo(libraryBranch.getAddress());
    }

    @Test
    public void testReadLibraryBranchs()
    {
        // given
        LibraryBranch libraryBranch1 = new LibraryBranch(1L, "Library of Congress", "101 Independence Ave SE, Washington, DC 20540");
        LibraryBranch libraryBranch2 = new LibraryBranch(2L, "Library of Alexandria", "Alexandria, Egypt, Ancient");
        List<LibraryBranch> libraryBranches = Arrays.asList(libraryBranch1, libraryBranch2);

        when(libraryBranchService.getLibraryBranches()).thenReturn(libraryBranches);

        // when
        ResponseEntity<List<LibraryBranch>> result = libraryBranchController.readLibraryBranchs();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(libraryBranch1.getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(libraryBranch2.getName());
    }

    @Test
    public void testReadLibraryBranchById() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(1L, "Library of Congress", "101 Independence Ave SE, Washington, DC 20540");

        when(libraryBranchService.getLibraryBranch(libraryBranch.getId())).thenReturn(libraryBranch);

        // when
        ResponseEntity<LibraryBranch> result = libraryBranchController.readLibraryBranchById(libraryBranch.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(libraryBranch.getName());
        assertThat(result.getBody().getAddress()).isEqualTo(libraryBranch.getAddress());
    }

    @Test
    public void testUpdateLibraryBranch() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(1L, "Library of Congress", "101 Independence Ave SE, Washington, DC 20540");

        when(libraryBranchService.setLibraryBranch(any(LibraryBranch.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<LibraryBranch> result = libraryBranchController.updateLibraryBranch(libraryBranch.getId(), libraryBranch);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(libraryBranch.getName());
        assertThat(result.getBody().getAddress()).isEqualTo(libraryBranch.getAddress());
    }

    @Test
    public void testDeleteLibraryBranch() {
        // given
        LibraryBranch libraryBranch = new LibraryBranch(1L, "Library of Congress", "101 Independence Ave SE, Washington, DC 20540");

        doNothing().when(libraryBranchService).deleteLibraryBranch(isA(Long.class));

        // when
        ResponseEntity<LibraryBranch> result = libraryBranchController.deleteLibraryBranch(libraryBranch.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
