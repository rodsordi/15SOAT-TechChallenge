package br.com.fiap.mecanica.domain.entity;

import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static br.com.fiap.mecanica.domain.entity.factory.OrdemDeServicoFactory.criarOrdemDeServicoDirector;
import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdemDeServicoTest {

    private OrdemDeServico ordemDeServico;

    @DisplayName("Quando diagnosticar ordem de serviço")
    @Nested
    class Diagnosticar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado uma ordem de serviço com status RECEBIDA")
            @Test
            void test1() {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(RECEBIDA)
                        .build();
                //Quando
                ordemDeServico.diagnosticar();
                //Então
                assertThat(ordemDeServico.getStatus())
                        .isEqualTo(EM_DIAGNOSTICO);
            }
        }

        @DisplayName("Então deve retornar mensagem de erro")
        @Nested
        class Falha {

            @DisplayName("Dado uma ordem de serviço com status {status}")
            @CsvSource(value = {
                    "EM_DIAGNOSTICO       | Ordem de Serviço com situação EM_DIAGNOSTICO, não pode ser atualizada para situação EM_DIAGNOSTICO",
                    "AGUARDANDO_APROVACAO | Ordem de Serviço com situação AGUARDANDO_APROVACAO, não pode ser atualizada para situação EM_DIAGNOSTICO",
                    "EM_EXECUCAO          | Ordem de Serviço com situação EM_EXECUCAO, não pode ser atualizada para situação EM_DIAGNOSTICO",
                    "FINALIZADA           | Ordem de Serviço com situação FINALIZADA, não pode ser atualizada para situação EM_DIAGNOSTICO",
                    "ENTREGUE             | Ordem de Serviço com situação ENTREGUE, não pode ser atualizada para situação EM_DIAGNOSTICO",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(OrdemDeServicoStatus status, String mensagemEsperada) {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(status)
                        .build();
                //Quando
                var atual = assertThrows(BusinessException.class,
                        () -> ordemDeServico.diagnosticar());
                //Então
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("Quando aguardar aprovacao ordem de serviço")
    @Nested
    class AguardarAprovacao {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado uma ordem de serviço com status RECEBIDA")
            @Test
            void test1() {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(EM_DIAGNOSTICO)
                        .build();
                //Quando
                ordemDeServico.aguardarAprovacao();
                //Então
                assertThat(ordemDeServico.getStatus())
                        .isEqualTo(AGUARDANDO_APROVACAO);
            }
        }

        @DisplayName("Então deve retornar mensagem de erro")
        @Nested
        class Falha {

            @DisplayName("Dado uma ordem de serviço com status {status}")
            @CsvSource(value = {
                    "RECEBIDA             | Ordem de Serviço com situação RECEBIDA, não pode ser atualizada para situação AGUARDANDO_APROVACAO",
                    "AGUARDANDO_APROVACAO | Ordem de Serviço com situação AGUARDANDO_APROVACAO, não pode ser atualizada para situação AGUARDANDO_APROVACAO",
                    "EM_EXECUCAO          | Ordem de Serviço com situação EM_EXECUCAO, não pode ser atualizada para situação AGUARDANDO_APROVACAO",
                    "FINALIZADA           | Ordem de Serviço com situação FINALIZADA, não pode ser atualizada para situação AGUARDANDO_APROVACAO",
                    "ENTREGUE             | Ordem de Serviço com situação ENTREGUE, não pode ser atualizada para situação AGUARDANDO_APROVACAO",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(OrdemDeServicoStatus status, String mensagemEsperada) {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(status)
                        .build();
                //Quando
                var atual = assertThrows(BusinessException.class,
                        () -> ordemDeServico.aguardarAprovacao());
                //Então
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("Quando executar ordem de serviço")
    @Nested
    class Executar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado uma ordem de serviço com status RECEBIDA")
            @Test
            void test1() {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(AGUARDANDO_APROVACAO)
                        .build();
                //Quando
                ordemDeServico.executar();
                //Então
                assertThat(ordemDeServico.getStatus())
                        .isEqualTo(EM_EXECUCAO);
            }
        }

        @DisplayName("Então deve retornar mensagem de erro")
        @Nested
        class Falha {

            @DisplayName("Dado uma ordem de serviço com status {status}")
            @CsvSource(value = {
                    "RECEBIDA             | Ordem de Serviço com situação RECEBIDA, não pode ser atualizada para situação EM_EXECUCAO",
                    "EM_DIAGNOSTICO       | Ordem de Serviço com situação EM_DIAGNOSTICO, não pode ser atualizada para situação EM_EXECUCAO",
                    "EM_EXECUCAO          | Ordem de Serviço com situação EM_EXECUCAO, não pode ser atualizada para situação EM_EXECUCAO",
                    "FINALIZADA           | Ordem de Serviço com situação FINALIZADA, não pode ser atualizada para situação EM_EXECUCAO",
                    "ENTREGUE             | Ordem de Serviço com situação ENTREGUE, não pode ser atualizada para situação EM_EXECUCAO",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(OrdemDeServicoStatus status, String mensagemEsperada) {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(status)
                        .build();
                //Quando
                var atual = assertThrows(BusinessException.class,
                        () -> ordemDeServico.executar());
                //Então
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("Quando finalizar ordem de serviço")
    @Nested
    class Finalizar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado uma ordem de serviço com status RECEBIDA")
            @Test
            void test1() {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(EM_EXECUCAO)
                        .build();
                //Quando
                ordemDeServico.finalizar();
                //Então
                assertThat(ordemDeServico.getStatus())
                        .isEqualTo(FINALIZADA);
            }
        }

        @DisplayName("Então deve retornar mensagem de erro")
        @Nested
        class Falha {

            @DisplayName("Dado uma ordem de serviço com status {status}")
            @CsvSource(value = {
                    "RECEBIDA             | Ordem de Serviço com situação RECEBIDA, não pode ser atualizada para situação FINALIZADA",
                    "EM_DIAGNOSTICO       | Ordem de Serviço com situação EM_DIAGNOSTICO, não pode ser atualizada para situação FINALIZADA",
                    "AGUARDANDO_APROVACAO | Ordem de Serviço com situação AGUARDANDO_APROVACAO, não pode ser atualizada para situação FINALIZADA",
                    "FINALIZADA           | Ordem de Serviço com situação FINALIZADA, não pode ser atualizada para situação FINALIZADA",
                    "ENTREGUE             | Ordem de Serviço com situação ENTREGUE, não pode ser atualizada para situação FINALIZADA",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(OrdemDeServicoStatus status, String mensagemEsperada) {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(status)
                        .build();
                //Quando
                var atual = assertThrows(BusinessException.class,
                        () -> ordemDeServico.finalizar());
                //Então
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("Quando entregar ordem de serviço")
    @Nested
    class Entregar {

        @DisplayName("Então deve executar com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Dado uma ordem de serviço com status RECEBIDA")
            @Test
            void test1() {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(FINALIZADA)
                        .build();
                //Quando
                ordemDeServico.entregar();
                //Então
                assertThat(ordemDeServico.getStatus())
                        .isEqualTo(ENTREGUE);
            }
        }

        @DisplayName("Então deve retornar mensagem de erro")
        @Nested
        class Falha {

            @DisplayName("Dado uma ordem de serviço com status {status}")
            @CsvSource(value = {
                    "RECEBIDA             | Ordem de Serviço com situação RECEBIDA, não pode ser atualizada para situação ENTREGUE",
                    "EM_DIAGNOSTICO       | Ordem de Serviço com situação EM_DIAGNOSTICO, não pode ser atualizada para situação ENTREGUE",
                    "AGUARDANDO_APROVACAO | Ordem de Serviço com situação AGUARDANDO_APROVACAO, não pode ser atualizada para situação ENTREGUE",
                    "EM_EXECUCAO          | Ordem de Serviço com situação EM_EXECUCAO, não pode ser atualizada para situação ENTREGUE",
                    "ENTREGUE             | Ordem de Serviço com situação ENTREGUE, não pode ser atualizada para situação ENTREGUE",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(OrdemDeServicoStatus status, String mensagemEsperada) {
                //Dado
                ordemDeServico = criarOrdemDeServicoDirector()
                        .comTodosOsCampos()
                        .status(status)
                        .build();
                //Quando
                var atual = assertThrows(BusinessException.class,
                        () -> ordemDeServico.entregar());
                //Então
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }
}