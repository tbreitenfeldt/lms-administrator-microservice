package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.PublisherDAO;
import com.smoothstack.lms.adminservice.entity.Publisher;
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
public class PublisherServiceTest {
    private final static long ID1 = 1L;
    private final static long ID2 = 2L;
    private final static String NAME1 = "Random House";
    private final static String NAME2 = "Pakt";
    private final static String ADDRESS1 = "London, UK";
    private final static String ADDRESS2 = "New York, NY";
    private final static String PHONE_NUMBER1 = "555-5632";
    private final static String PHONE_NUMBER2 = "555-7854";

    @Mock
    private PublisherDAO publisherDAO;

    @InjectMocks
    private PublisherService publisherService;

    @Test
    public void testGetPublisher() {
        // given
        Publisher publisher = new Publisher(ID1, NAME1, ADDRESS1, PHONE_NUMBER1);
        when(publisherDAO.findById(ID1)).thenReturn(Optional.of(publisher));

        // when
        Publisher returned = publisherService.getPublisher(ID1);

        // then
        verify(publisherDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(publisherDAO);
        assertEquals(publisher, returned);
    }

    @Test
    public void testGetPublishers() {
        // given
        Publisher publisher1 = new Publisher(ID1, NAME1, ADDRESS1, PHONE_NUMBER1);
        Publisher publisher2 = new Publisher(ID2, NAME2, ADDRESS2, PHONE_NUMBER2);
        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);
        when(publisherDAO.findAll()).thenReturn(publishers);

        // when
        List<Publisher> returned = publisherService.getPublishers();

        // then
        verify(publisherDAO, times(1)).findAll();
        verifyNoMoreInteractions(publisherDAO);
        assertEquals(publishers, returned);
    }

    @Test
    public void testSetPublisherWithNewPublisher() {
        // given
        Publisher created = new Publisher(null, NAME1, ADDRESS1, PHONE_NUMBER1);
        Publisher persisted = new Publisher(ID1, NAME1, ADDRESS1, PHONE_NUMBER1);
        when(publisherDAO.save(created)).thenReturn(persisted);

        // when
        Publisher returned = publisherService.setPublisher(created);

        // then
        ArgumentCaptor<Publisher> publisherArgument = ArgumentCaptor.forClass(Publisher.class);
        verify(publisherDAO, times(1)).save(publisherArgument.capture());
        verifyNoMoreInteractions(publisherDAO);
        assertPublisher(created, publisherArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetPublisherWithExistingPublisher() {
        // given
        Publisher publisher = new Publisher(ID1, NAME1, ADDRESS1, PHONE_NUMBER1);
        when(publisherDAO.save(publisher)).thenReturn(publisher);

        // when
        Publisher returned = publisherService.setPublisher(publisher);

        // then
        ArgumentCaptor<Publisher> publisherArgument = ArgumentCaptor.forClass(Publisher.class);
        verify(publisherDAO, times(1)).save(publisherArgument.capture());
        verifyNoMoreInteractions(publisherDAO);
        assertPublisher(publisher, publisherArgument.getValue());
        assertEquals(publisher, returned);
    }

    @Test
    public void testDeletePublisher() {
        // given
        Publisher publisher = new Publisher(ID1, NAME1, ADDRESS1, PHONE_NUMBER1);
        when(publisherDAO.findById(publisher.getId())).thenReturn(Optional.of(publisher));

        // when
        publisherService.deletePublisher(publisher.getId());

        // then
        verify(publisherDAO, times(1)).findById(publisher.getId());
        verify(publisherDAO, times(1)).delete(publisher);
        verifyNoMoreInteractions(publisherDAO);
    }

    private void assertPublisher(Publisher expected, Publisher actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }
}
