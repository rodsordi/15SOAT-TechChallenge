package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.commons.config.RestControllerTestConfig;
import br.com.fiap.garage.domain.use_case.InventoryMaterialCreationUseCase;
import br.com.fiap.garage.domain.use_case.InventoryMaterialSearchUseCase;
import br.com.fiap.garage.domain.use_case.InventoryMaterialUpdateUseCase;
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

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.InventoryMaterialFactory.create_InventoryMaterial;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = InventoryMaterialController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestControllerTestConfig.class)
class InventoryMaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockitoBean
    private InventoryMaterialSearchUseCase inventoryMaterialSearchUseCase;

    @MockitoBean
    private InventoryMaterialCreationUseCase inventoryMaterialCreationUseCase;

    @MockitoBean
    private InventoryMaterialUpdateUseCase inventoryMaterialUpdateUseCase;

    @DisplayName("When finding all inventory materials")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(inventoryMaterialSearchUseCase.findAll(any()))
                        .thenAnswer(invocationOnMock -> {
                            var inventoryMaterials = List.of(
                                    create_InventoryMaterial().withAllFields(),
                                    create_InventoryMaterial().withAllFields(),
                                    create_InventoryMaterial().withAllFields());
                            return new PageImpl<>(inventoryMaterials);
                        });
            }

            @DisplayName("Given all filters")
            @Test
            void test1() throws Exception {
                //Scenario
                when(inventoryMaterialSearchUseCase.findAll(any()))
                        .thenAnswer(invocationOnMock -> {
                            assertThatObject(invocationOnMock.getArgument(0))
                                    .hasNoEmptyFields();
                            var inventoryMaterials = List.of(
                                    create_InventoryMaterial().withAllFields(),
                                    create_InventoryMaterial().withAllFields(),
                                    create_InventoryMaterial().withAllFields());
                            return new PageImpl<>(inventoryMaterials);
                        });
                //When
                mockMvc.perform(get("/v1/inventory-materials")
                                .queryParam("type", "SPARE_PART")
                                .queryParam("name", "John Doe")
                                .queryParam("costFrom", "1.99")
                                .queryParam("costTo", "9.99")
                                .queryParam("createdAtFrom", "2025-01-01")
                                .queryParam("createdAtTo", "2025-12-31")
                                .queryParam("updatedAtFrom", "2025-01-01")
                                .queryParam("updatedAtTo", "2025-12-31"))
                        //Then
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content.[*].id", hasSize(3)))
                ;
            }
            
            @DisplayName("Given empty filters")
            @Test
            void test2() throws Exception {
                //When
                mockMvc.perform(get("/v1/inventory-materials"))
                        //Then
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content.[*].id", hasSize(3)))
                ;
            }
        }
    }
}