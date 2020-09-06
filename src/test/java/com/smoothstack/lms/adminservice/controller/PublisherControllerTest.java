package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.Publisher;
import com.smoothstack.lms.adminservice.service.PublisherService;
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
public class PublisherControllerTest {
    @InjectMocks
    private PublisherController publisherController;

    @Mock
    PublisherService publisherService;

    @Test
    public void testCreatePublisher() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(publisherService.setPublisher(any(Publisher.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        Publisher publisher = new Publisher(null, "Penguin", "London, UK", "555-5555");
        ResponseEntity<Publisher> result = publisherController.createPublisher(publisher);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(publisher.getName());
    }

    @Test
    public void testReadPublishers()
    {
        // given
        Publisher publisher1 = new Publisher(1L, "Penguin", "London, UK", "555-5555");
        Publisher publisher2 = new Publisher(2L, "Pakt", "New York, USA", "666-6666");
        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);

        when(publisherService.getPublishers()).thenReturn(publishers);

        // when
        ResponseEntity<List<Publisher>> result = publisherController.readPublishers();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(publisher1.getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(publisher2.getName());
    }

    @Test
    public void testReadPublisherById() {
        // given
        Publisher publisher = new Publisher(1L, "Penguin", "London, UK", "555-5555");

        when(publisherService.getPublisher(publisher.getId())).thenReturn(publisher);

        // when
        ResponseEntity<Publisher> result = publisherController.readPublisherById(publisher.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(publisher.getName());
    }

    @Test
    public void testUpdatePublisher() {
        // given
        Publisher publisher = new Publisher(1L, "Penguin", "London, UK", "555-5555");

        when(publisherService.setPublisher(any(Publisher.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<Publisher> result = publisherController.updatePublisher(publisher.getId(), publisher);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(publisher.getName());
    }

    @Test
    public void testDeletePublisher() {
        // given
        Publisher publisher = new Publisher(1L, "Penguin", "London, UK", "555-5555");

        doNothing().when(publisherService).deletePublisher(isA(Long.class));

        // when
        ResponseEntity<Publisher> result = publisherController.deletePublisher(publisher.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}

