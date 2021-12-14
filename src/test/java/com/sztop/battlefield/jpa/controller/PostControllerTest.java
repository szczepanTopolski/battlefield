package com.sztop.battlefield.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sztop.battlefield.jpa.model.Post;
import com.sztop.battlefield.jpa.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Profile("jpa")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional // Nastąpi rollback po wykonaniu testu, dzięki czemu baza nie zostanie zaśmiecona testowymi postami! :)
    void shouldGetSinglePost() throws Exception {
//        mockMvc.perform(get("/posts/1"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$.id", Matchers.is(1))); Example with json path
        //given
        Post givenPost = new Post();
        givenPost.setTitle("Test");
        givenPost.setContent("Test content");
        givenPost.setCreated(LocalDateTime.now());
        postRepository.save(givenPost);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/posts/" + givenPost.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(givenPost.getId());
        assertThat(post.getTitle()).isEqualTo("Test");
        assertThat(post.getContent()).isEqualTo("Test content");
    }
}