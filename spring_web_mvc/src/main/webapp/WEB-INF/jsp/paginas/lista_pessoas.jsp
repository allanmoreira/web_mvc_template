<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<button type="button" href="#modalNovaPessoa" data-toggle="modal"
        class="btn btn-primary glyphicon-plus"> Cadastrar Pessoa</button><br><br>

<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">Pessoas Cadastradas</h3>
            </div>
            <div class="box-body">
                <table id="tabela_pessoas" class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="listaPessoas" items="${listaPessoas }">
                            <tr>
                                <td>${listaPessoas.nome }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%-- modal nova pessoa --%>
<div id="modalNovaPessoa" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Cadastrar Nova Pessoa</h4>
            </div>
            <div class="modal-body">
                <form id="nova_pessoa_form"
                      name="nova_pessoa_form"
                      class="form-horizontal" method="post" role="form"
                      accept-charset="iso-8859-1,utf-8">

                    <label>Nome</label>
                    <input type="text" class="form-control"
                           id="nome_nova_pessoa" name="nome_nova_pessoa"
                           placeholder="Informe o nome..." aria-describedby="basic-addon1">

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="botao_salvar_nova_pessoa">Salvar</button>
            </div>
        </div>
    </div>
</div>
<%-- modal nova pessoa --%>




