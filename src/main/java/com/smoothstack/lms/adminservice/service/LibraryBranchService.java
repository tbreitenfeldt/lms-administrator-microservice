package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.LibraryBranchDAO;
import com.smoothstack.lms.adminservice.entity.LibraryBranch;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryBranchService {
    @Autowired
    private LibraryBranchDAO libraryBranchDAO;

    public LibraryBranch getLibraryBranch(long id) {
        Optional<LibraryBranch> libraryBranch = libraryBranchDAO.findById(id);
        libraryBranch.orElseThrow(()-> new IllegalRelationReferenceException("No libraryBranch with id " + id));
        return libraryBranch.get();
    }
    public List<LibraryBranch> getLibraryBranches() {
        return libraryBranchDAO.findAll();
    }

    public LibraryBranch setLibraryBranch(LibraryBranch libraryBranch) {
        return libraryBranchDAO.save(libraryBranch);
    }

    public void deleteLibraryBranch(long id) {
        LibraryBranch libraryBranch = getLibraryBranch(id);
        libraryBranchDAO.delete(libraryBranch);
    }
}
