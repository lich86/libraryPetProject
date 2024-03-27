package com.chervonnaya.library.controller;

import com.chervonnaya.library.BaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.chervonnaya.library.controller.ControllerTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.JsonUtil.dtoToString;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class AuthorControllerTest extends BaseTest {

    private final MockMvc mockMvc;

    @Test
    void getAuthorByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_AUTHOR_URL))
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
                        .content(dtoToString(AUTHOR_DTO)))
                .andExpect(status().isCreated());
    }

}
