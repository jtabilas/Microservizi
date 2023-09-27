package com.example.accountservice;


import com.example.accountservice.controller.AccountController;
import com.example.accountservice.model.Account;
import com.example.accountservice.model.Role;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.security.filter.JwtTokenFilter;
import com.example.accountservice.security.utils.JwtTokenProvider;
import com.example.accountservice.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerUnitTest {

    // evita di utilizzare la logica di business
    @MockBean
    private AccountService accountService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenFilter jwtTokenFilter;


    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AccountRepository repo;

    @MockBean
    EntityManager entityManager;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Account> listOfAccounts;


    @BeforeEach
    void  setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(springSecurity()).build();

        listOfAccounts = new ArrayList<>() {
            {
                Account account = new Account();
                account.setIdAccount(1);
                account.setName("Lorenzo");
                account.setSurname("Effe");
                account.setCity("Roma");
                account.setAddress("Via Nazionale 123");
                account.setAge(21);
                account.setUsername("Pippo");
                account.setEmail("lorenzo@gmail.com");
                account.setPassword("1234");
                account.setRole(Role.USER);
                add(account);
                account = new Account();
                account.setIdAccount(2);
                account.setName("Pippo");
                account.setSurname("Sowlo");
                account.setCity("Roma");
                account.setAddress("Via Conte 123");
                account.setAge(29);
                account.setUsername("sowlo");
                account.setEmail("pippo@gmail.com");
                account.setPassword("1234");
                account.setRole(Role.USER);
                add(account);
            }
        };
    }


    @Test
    @WithMockUser
    public void testGetAllAccount() throws  Exception {

        //given
        when(accountService.getAllAccount()).thenReturn(listOfAccounts);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listOfAccounts.size())))
        ;
    }

    @Test
    @WithMockUser
    public void testAccountById() throws  Exception {

        //given
        Integer accountId = 1;
        Account account = Account.builder()
                .idAccount(accountId)
                .name("Lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountService.getAccountById(accountId)).willReturn(Optional.ofNullable(account));

        //when
        ResultActions response = mockMvc.perform(get("/api/accounts/{id}", accountId));

        //then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount").value(accountId))
                .andDo(print());

    }

    @Test
    @WithMockUser
    public void testInvalidAccountById() throws  Exception {

        //given
        Integer accountId = 1;
        Account account = Account.builder()
                .idAccount(accountId)
                .name("Lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountService.getAccountById(accountId)).willReturn(Optional.ofNullable(account));

        //when
        ResultActions response = mockMvc.perform(get("/api/accounts/{id}", 2));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @WithMockUser
    public void testAccountByName() throws  Exception {

        //given
        Integer accountId = 1;
        String nome= "lollo";
        Account account = Account.builder()
                .idAccount(accountId)
                .name("lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountService.getAccountByName(nome)).willReturn(Optional.ofNullable(account));

        //when
        ResultActions response = mockMvc.perform(get("/api/accounts/name/{name}", nome));

        //then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(nome))
                .andDo(print());

    }


    @Test
    @WithMockUser
    public void testInvalidAccountByName() throws  Exception {

        //given
        Integer accountId = 1;
        String nome= "pippo";
        Account account = Account.builder()
                .idAccount(accountId)
                .name("lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountService.getAccountByName(nome)).willReturn(Optional.ofNullable(account));

        //when
        ResultActions response = mockMvc.perform(get("/api/accounts/name/{name}", nome));

        //then
        response.andExpect(status().isNotFound())
                .andDo(print());

    }


    @Test
    @WithMockUser
    public void testDeleteAccountById() throws  Exception {

        //given
        int accountId = 1;
        willDoNothing().given(accountService).deleteAccountById(accountId);

        //when
        ResultActions response = mockMvc.perform(delete("/api/deleteAccount/{id}", accountId).with(csrf()));

        //then
        response.andExpect(status().isOk())
                .andDo(print());

    }


}
