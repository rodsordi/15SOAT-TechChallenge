package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.commons.config.RestControllerTestConfig;
import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.use_case.OwnerCreationUseCase;
import br.com.fiap.garage.domain.use_case.OwnerSearchUseCase;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.factory.OwnerDtoFactory.Request.createOwnerDto_Request;
import static br.com.fiap.garage.domain.entity.factory.OwnerFactory.createOwner;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.text.MessageFormat.format;
import static java.util.UUID.fromString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = OwnerController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestControllerTestConfig.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockitoBean
    private OwnerCreationUseCase ownerCreationUseCase;

    @MockitoBean
    private OwnerSearchUseCase ownerSearchUseCase;

    @DisplayName("When creating Owner")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(ownerCreationUseCase.create(any()))
                        .thenAnswer(invocationOnMock -> {
                            Owner owner = invocationOnMock.getArgument(0);
                            setField(owner, "id", fromString("7a403fc9-3c96-408c-984f-1fea2729b59f"));
                            return owner;
                        });
            }

            @DisplayName("Given a owner with all fields")
            @Test
            void test1() throws Exception {
                //Given
                var requestBody = createOwnerDto_Request()
                        .withAllFields();
                //When
                mockMvc.perform(post("/v1/owners")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .characterEncoding(UTF_8.name())
                                .content(gson.toJson(requestBody)))
                        //Then
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is("7a403fc9-3c96-408c-984f-1fea2729b59f")));
            }
        }
    }

    @DisplayName("When finding owner by id")
    @Nested
    class FindById {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(ownerSearchUseCase.findById(any()))
                        .thenAnswer(invocationOnMock -> {
                            UUID ownerId = invocationOnMock.getArgument(0);
                            var owner = createOwner().withAllFields();
                            setField(owner, "id", ownerId);
                            return owner;
                        });
            }

            @DisplayName("Given a valid ownerId")
            @Test
            void test1() throws Exception {
                //Given
                var ownerId = "b17555de-3cb8-4ef5-8b43-6e3b3614d2f9";
                //When
                mockMvc.perform(get(format("/v1/owners/{0}", ownerId)))
                        //Then
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is("b17555de-3cb8-4ef5-8b43-6e3b3614d2f9")))
                ;
            }
        }
    }

    @DisplayName("When finding all owners")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(ownerSearchUseCase.findAll(any()))
                        .thenAnswer(invocationOnMock -> {
                            var owners = List.of(
                                    createOwner().withAllFields(),
                                    createOwner().withAllFields(),
                                    createOwner().withAllFields());
                            return new PageImpl<>(owners);
                        });
            }

            @DisplayName("Given empty filters")
            @Test
            void test1() throws Exception {
                //When
                mockMvc.perform(get("/v1/owners"))
                        //Then
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content.[*].id", hasSize(3)))
                ;
            }
        }
    }
}