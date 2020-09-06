package com.smoothstack.lms.adminservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.lms.adminservice.controller.*;
import com.smoothstack.lms.adminservice.dao.*;
import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.entity.Book;
import com.smoothstack.lms.adminservice.entity.BookLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdministratorMicroserviceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookLoanDAO bookLoanDAO;

    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private  LibraryBranchDAO libraryBranchDAO;

    @Autowired
    private PublisherDAO publisherDAO;

    @Autowired
    private AuthorController authorController;

    @Autowired
    private BookController bookController;

    @Autowired
    private BookLoanController bookLoanController;

    @Autowired
    private BorrowerController borrowerController;

    @Autowired
    private GenreController genreController;

    @Autowired
    private LibraryBranchController libraryBranchController;

    @Autowired
    private PublisherController publisherController;

	@Test
	public void contextLoads() {
	    assertNotNull(authorController);
        assertNotNull(bookController);
        assertNotNull(bookLoanController);
        assertNotNull(borrowerController);
        assertNotNull(genreController);
        assertNotNull(libraryBranchController);
        assertNotNull(publisherController);
	}

	@Test
    public void getAuthors() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/lms/admin/author"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<Author> response = objectMapper.readValue(contentAsString, ArrayList.class);

        assertTrue(response.size() > 0);
    }

    @Test
    public void getAuthorByIdWhenExists() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/lms/admin/author/1"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Author response = objectMapper.readValue(contentAsString, Author.class);

        assertEquals(1L, response.getId());
    }
}
