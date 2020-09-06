package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.BookLoanDAO;
import com.smoothstack.lms.adminservice.dao.BorrowerDAO;
import com.smoothstack.lms.adminservice.entity.BookLoan;
import com.smoothstack.lms.adminservice.entity.Borrower;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowerService {
    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private BookLoanDAO bookLoanDAO;

    public Borrower getBorrower(long id) {
        Optional<Borrower> borrower = borrowerDAO.findById(id);
        borrower.orElseThrow(()-> new IllegalRelationReferenceException("No borrower with id " + id));
        return borrower.get();
    }

    public List<Borrower> getBorrowers() {
        return borrowerDAO.findAll();
    }

    public Borrower setBorrower(Borrower Borrower) {
        return borrowerDAO.save(Borrower);
    }

    public void deleteBorrower(long id) {
        Borrower borrower = getBorrower(id);
        borrowerDAO.delete(borrower);
    }

    public Optional<BookLoan> getBookLoan(BookLoan.BookLoanId id) {
        return bookLoanDAO.findById(id);
    }

    public BookLoan updateDueDate(BookLoan bookLoan) {
        return bookLoanDAO.save(bookLoan);
    }
}
