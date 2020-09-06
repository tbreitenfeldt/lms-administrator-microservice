package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.Borrower;
import com.smoothstack.lms.adminservice.service.BorrowerService;
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
public class BorrowerControllerTest {
    @InjectMocks
    private BorrowerController borrowerController;

    @Mock
    BorrowerService borrowerService;

    @Test
    public void testCreateBorrower() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(borrowerService.setBorrower(any(Borrower.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        Borrower borrower = new Borrower(null, "Lokesh Gupta","Beverly Hills 90210",  "555-5555");
        ResponseEntity<Borrower> result = borrowerController.createBorrower(borrower);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(borrower.getName());
    }

    @Test
    public void testReadBorrowers()
    {
        // given
        Borrower borrower1 = new Borrower(1L, "Lokesh Gupta","Beverly Hills 90210",  "555-5555");
        Borrower borrower2 = new Borrower(2L, "Mr. Pibbles","1247 Oak Lane",  "867-5309");
        List<Borrower> borrowers = Arrays.asList(borrower1, borrower2);

        when(borrowerService.getBorrowers()).thenReturn(borrowers);

        // when
        ResponseEntity<List<Borrower>> result = borrowerController.readBorrowers();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getAddress()).isEqualTo(borrower1.getAddress());
        assertThat(result.getBody().get(1).getPhoneNumber()).isEqualTo(borrower2.getPhoneNumber());
    }

    @Test
    public void testReadBorrowerById() {
        // given
        Borrower borrower = new Borrower(2L, "Mr. Pibbles","1247 Oak Lane",  "867-5309");

        when(borrowerService.getBorrower(borrower.getId())).thenReturn(borrower);

        // when
        ResponseEntity<Borrower> result = borrowerController.readBorrowerById(borrower.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(borrower.getName());
    }

    @Test
    public void testUpdateBorrower() {
        // given
        Borrower borrower = new Borrower(2L, "Mr. Pibbles","1247 Oak Lane",  "867-5309");

        when(borrowerService.setBorrower(any(Borrower.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<Borrower> result = borrowerController.updateBorrower(borrower.getId(), borrower);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(borrower.getName());
    }

    @Test
    public void testDeleteBorrower() {
        // given
        Borrower borrower = new Borrower(2L, "Mr. Pibbles","1247 Oak Lane",  "867-5309");

        doNothing().when(borrowerService).deleteBorrower(isA(Long.class));

        // when
        ResponseEntity<Borrower> result = borrowerController.deleteBorrower(borrower.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
