package com.chervonnaya.library.controller;

import com.chervonnaya.library.BaseTest;
import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.repository.AuthorRepository;
import com.chervonnaya.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.chervonnaya.library.controller.ControllerTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.JsonUtil.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class AuthorControllerTest extends BaseTest {

    private final MockMvc mockMvc;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Test
    void getAuthorByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void addAuthorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AUTHOR_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoToString(AUTHOR_DTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void addAuthorAsAdminTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AUTHOR_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToString(AUTHOR_DTO).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(AUTHOR_DTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(AUTHOR_DTO.getLastName()))
                .andExpect(jsonPath("$.middleName").value(AUTHOR_DTO.getMiddleName()))
                .andExpect(jsonPath("$.penName").value(AUTHOR_DTO.getPenName()));
    }

    @Test
    void updateAuthorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(AUTHOR_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToString(AUTHOR_DTO)))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateAuthorAsAdminTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_URL + "/1")).andReturn();
        AuthorDTO authorDTO = stringToDto(result.getResponse().getContentAsString(StandardCharsets.UTF_8), AuthorDTO.class);
        assertNotEquals(AUTHOR_PEN_NAME, authorDTO.getPenName());
        authorDTO.setPenName(AUTHOR_PEN_NAME);
        mockMvc.perform(MockMvcRequestBuilders.put(AUTHOR_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.penName").value(AUTHOR_PEN_NAME));
    }


    @Test
    void deleteAuthorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_URL + "/1"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete(AUTHOR_URL + "/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteAuthorAsAdminTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_URL + "/1"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete(AUTHOR_URL + "/1"))
                .andExpect(status().isOk());

        Optional<Author> deletedAuthor = authorRepository.findById(1L);
        assertTrue(deletedAuthor.isEmpty());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllBooksByAuthor() throws Exception {
        Set<Book> books = bookRepository.getAllByAuthor(1L);
        List<String> bookTitles = books.stream().map(Book::getOriginalTitle).sorted().toList();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(AUTHOR_URL + "/1/book"))
                .andReturn();
        Set<Book> actualBooks = stringToListOfEntities(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Book.class);
        List<String> actualBookTitles = actualBooks.stream().map(Book::getOriginalTitle).sorted().toList();
        assertNotNull(actualBooks);
        assertEquals(bookTitles, actualBookTitles);
    }

}
