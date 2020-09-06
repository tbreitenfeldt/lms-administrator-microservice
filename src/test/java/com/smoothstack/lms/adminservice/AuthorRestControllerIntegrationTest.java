package com.smoothstack.lms.adminservice;
//package com.smoothstack.december.administratormicroservice;
//
//import com.smoothstack.december.administratormicroservice.controller.AuthorController;
//import com.smoothstack.december.administratormicroservice.entity.Author;
//import com.smoothstack.december.administratormicroservice.service.AuthorService;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringRunner.class)
//@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.class)
//@WebMvcTest(AuthorController.class)
//public class AuthorRestControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private AuthorService service;
//
//    @Test
//    public void givenAuthors_whetGetAuthors_thenReturnJsonArray() throws Exception {
//        Author homer = new Author(1, "Homer");
//        List<Author> allAuthors = Arrays.asList(homer);
//        given(service.getAuthors()).willReturn(allAuthors);
//        mvc.perform(get("/v1/lms/administrator-service/authors")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is(homer.getName())));
//    }
//}
