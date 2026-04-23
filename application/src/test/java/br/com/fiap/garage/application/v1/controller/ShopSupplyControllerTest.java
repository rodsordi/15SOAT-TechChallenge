package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.commons.config.RestControllerTestConfig;
import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.use_case.ShopSupplyCreationUseCase;
import br.com.fiap.garage.domain.use_case.ShopSupplySearchUseCase;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.factory.ShopSupplyDtoFactory.create_ShopSupplyDto_Request;
import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.create_ShopSupply;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.text.MessageFormat.format;
import static java.util.UUID.fromString;
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
@SpringBootTest(classes = ShopSupplyController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestControllerTestConfig.class)
class ShopSupplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockitoBean
    private ShopSupplyCreationUseCase shopSupplyCreationUseCase;

    @MockitoBean
    private ShopSupplySearchUseCase shopSupplySearchUseCase;

    @DisplayName("When creating ShopSupply")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(shopSupplyCreationUseCase.create(any()))
                        .thenAnswer(invocationOnMock -> {
                            ShopSupply shopSupply = invocationOnMock.getArgument(0);
                            setField(shopSupply, "id", fromString("7a403fc9-3c96-408c-984f-1fea2729b59f"));
                            return shopSupply;
                        });
            }

            @DisplayName("Given a shopSupply with all fields")
            @Test
            void test1() throws Exception {
                //Given
                var requestBody = create_ShopSupplyDto_Request()
                        .withAllFields();
                //When
                mockMvc.perform(post("/v1/shop-supplies")
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

    @DisplayName("When finding shopSupply by id")
    @Nested
    class FindById {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(shopSupplySearchUseCase.findById(any()))
                        .thenAnswer(invocationOnMock -> {
                            UUID shopSupplyId = invocationOnMock.getArgument(0);
                            var shopSupply = create_ShopSupply().withAllFields();
                            setField(shopSupply, "id", shopSupplyId);
                            return shopSupply;
                        });
            }

            @DisplayName("Given a valid shopSupplyId")
            @Test
            void test1() throws Exception {
                //Given
                var shopSupplyId = "b17555de-3cb8-4ef5-8b43-6e3b3614d2f9";
                //When
                mockMvc.perform(get(format("/v1/shop-supplies/{0}", shopSupplyId)))
                        //Then
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is("b17555de-3cb8-4ef5-8b43-6e3b3614d2f9")))
                ;
            }
        }
    }
}