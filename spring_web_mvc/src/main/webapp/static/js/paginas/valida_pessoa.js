/**
 * Created by allan on 22/01/16.
 */

// Função ajax que envia os dados pra Servlet para salvar novos dados
$(function() {
	$('#botao_salvar_nova_pessoa').click(function () {
		if($('#nome_nova_pessoa').val() == "") {
			$.bootstrapGrowl("Preencha o campo!", {
				type: 'danger',
				align: 'center',
				width: 'auto',
				allow_dismiss: false
			});
		}
		else {
			$.ajax({
				url: 'cadastrar_pessoa',
				async: true,
				type: 'POST',
				dataType: 'json',
				data: $('#nova_pessoa_form').serialize(),
				success: function (data) {
					if (data.isValid) {
						$.bootstrapGrowl("Novo fundo salvo com sucesso!", {
							type: 'success',
							align: 'center',
							width: 'auto',
							allow_dismiss: false
						});

						var pessoa = data.pessoa;

						var linha_tabela =
							'<tr>' +
								'<td>' + pessoa.nome + '</td>' +
							'</tr>';

						$('#tabela_pessoas tbody').append(linha_tabela);

						$('#modalNovaPessoa').modal('hide');

						$('#nova_pessoa_form').each(function () {
							this.reset();
						});

					} else {
						$.bootstrapGrowl(data.msgErro, {
							type: 'danger',
							align: 'center',
							width: 'auto',
							allow_dismiss: false
						});
					}
				}
			});
		}
    });
});
