package com.example.accountservice;

import com.example.accountservice.controller.AuthController;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.security.dto.AuthenticationRequest;
import com.example.accountservice.security.dto.AuthenticationResponse;
import com.example.accountservice.security.dto.SingUpRequest;
import com.example.accountservice.security.service.AuthenticationService;
import com.example.accountservice.security.utils.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;



import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
public class AuthControllerUnitTest {

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AuthenticationResponse authenticationResponse;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SingUpRequest singUpRequest;

    @MockBean
    private AuthenticationRequest authenticationRequest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser
    public void testSingup() throws Exception{
        //given
        SingUpRequest singUpRequest = new SingUpRequest();
        singUpRequest.setName("lollo");
        singUpRequest.setSurname("verdi");
        singUpRequest.setCity("roma");
        singUpRequest.setAddress("via conte 23");
        singUpRequest.setAge(19);
        singUpRequest.setUsername("drast");
        singUpRequest.setEmail("lollo@gmail.com");
        singUpRequest.setPassword("1234");


        String signUpRequestJson = objectMapper.writeValueAsString(singUpRequest);
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyIsImlhdCI6MTY5NTMwMTI2MSwiZXhwIjoxNjk1MzAyMTYxfQ._sTQqj51NGkfzdSJ6QnGviqZ0lmi307BI14oEe-pYsexeGUb5WD98abDxvAHjsVO5RJ84Ipp_2zxZWAYxLt5Yw";
        given(authenticationService.singup(singUpRequest)).willReturn(new AuthenticationResponse(jwt,null));

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/singup").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(signUpRequestJson));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accessToken").exists()); // check if accessToken exists
        ;
    }


    @Test
    @WithMockUser
    public void testLogin() throws Exception{
        //given
        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("drast");
        authenticationRequest.setPassword("1234");


        given(authenticationService.login(authenticationRequest)).willReturn(authenticationResponse);
        String signUpRequestJson = objectMapper.writeValueAsString(authenticationRequest);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(signUpRequestJson));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        ;
    }



}
