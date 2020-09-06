package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.PublisherDAO;
import com.smoothstack.lms.adminservice.entity.Publisher;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherService {
    @Autowired
    private PublisherDAO publisherDAO;

    public Publisher getPublisher(long id) {
        Optional<Publisher> publisher = publisherDAO.findById(id);
        publisher.orElseThrow(()-> new IllegalRelationReferenceException("No publisher with id " + id));
        return publisher.get();
    }

    public List<Publisher> getPublishers() {
        return publisherDAO.findAll();
    }

    public Publisher setPublisher(Publisher publisher) {
        return publisherDAO.save(publisher);
    }

    public void deletePublisher(long id) {
        Publisher publisher = getPublisher(id);
        publisherDAO.delete(publisher);
    }
}
