package com.diamorph.blog.post;

import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.service.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    private static final String END_POINT_PATH = "/posts";

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private PostServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        PostDTO newPost = new PostDTO();

        String errorByName = "$.errors[?(@ == '%s')]";
        String requestBody = objectMapper.writeValueAsString(newPost);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(END_POINT_PATH)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName, "Title: Must not be blank").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName,"Body: Must not be blank").exists())
                .andDo(print());
    }

    @Test
    public void testAddShouldReturn400BadRequestWhenTitleLengthValidation() throws Exception {
        PostDTO newPost = new PostDTO();
        newPost.setTitle("12");

        String errorByName = "$.errors[?(@ == '%s')]";
        String requestBody = objectMapper.writeValueAsString(newPost);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(END_POINT_PATH)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName, "Title: Must have at least 3 characters").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName,"Body: Must not be blank").exists())
                .andDo(print());
    }

    @Test
    public void testAddShouldReturn400BadRequestWhenBodyLengthValidation() throws Exception {
        PostDTO newPost = new PostDTO();
        newPost.setBody("12");

        String errorByName = "$.errors[?(@ == '%s')]";
        String requestBody = objectMapper.writeValueAsString(newPost);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(END_POINT_PATH)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName,"Title: Must not be blank").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(errorByName, "Body: Must have at least 3 characters").exists())
                .andDo(print());
    }

    @Test void testAddShouldReturnOkWhenValid() throws Exception {
        PostDTO newPost = new PostDTO();
        String title = "Title";
        String body = "Body";
        newPost.setTitle(title);
        newPost.setBody(body);

        String requestBody = objectMapper.writeValueAsString(newPost);

        mockMvc.perform(MockMvcRequestBuilders
                .post(END_POINT_PATH)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value(body))
                .andDo(print());
        ;
    }
}