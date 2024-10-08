package atualizaAliquota.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import atualizaAliquota.model.Aliquota;
import atualizaAliquota.util.Conexao;
import atualizaAliquota.util.Log;

public class AliquotaDAO {
	private static final String SQL_BUSCAR_ALIQUOTAS = "SELECT id, (CASE\r\n"
			+ "    WHEN porcentagem = ROUND(porcentagem) THEN\r\n"
			+ "        TO_CHAR(porcentagem, 'FM999')\r\n"
			+ "    ELSE\r\n"
			+ "        TO_CHAR(porcentagem, 'FM999.99')\r\n"
			+ "END) as porcentagem, (CASE\r\n"
			+ "    WHEN reduzido = ROUND(reduzido) THEN\r\n"
			+ "        TO_CHAR(reduzido, 'FM999')\r\n"
			+ "    ELSE\r\n"
			+ "        TO_CHAR(reduzido, 'FM999.99')\r\n"
			+ "END) as reduzido, (CASE\r\n"
			+ "    WHEN porcentagemfcp = ROUND(porcentagemfcp) THEN\r\n"
			+ "        TO_CHAR(porcentagemfcp, 'FM999')\r\n"
			+ "    ELSE\r\n"
			+ "        TO_CHAR(porcentagemfcp, 'FM999.99')\r\n"
			+ "END) as porcentagemfcp, \r\n"
			+ "	(CASE\r\n"
			+ "    WHEN percentualicmsdesonerado = ROUND(percentualicmsdesonerado) THEN\r\n"
			+ "        TO_CHAR(percentualicmsdesonerado, 'FM999')\r\n"
			+ "    ELSE\r\n"
			+ "        TO_CHAR(percentualicmsdesonerado, 'FM999.99')\r\n"
			+ "END) as percentualicmsdesonerado,"
			+ "     situacaotributaria, id_aliquotapdv as idaliquotapdv FROM aliquota;";
	
	public List<Aliquota> getAliquotaParaCorrecao() {
		//executa a query que coleta os dados do bd
		
		Conexao conectaBanco = new Conexao();
		conectaBanco.getProperties();
		
		   try (Connection conexao = Conexao.conectar();
		        PreparedStatement preparedStatement = conexao.prepareStatement(SQL_BUSCAR_ALIQUOTAS)) {
			   	ResultSet rsAliquota = conexao.createStatement()
					.executeQuery(SQL_BUSCAR_ALIQUOTAS);
			
			ArrayList<Aliquota> aliquotas = new ArrayList<>();
			
			//percorre a query adicionando os atributos dentro da classe java 
			while (rsAliquota.next()) {
				Aliquota aliquota = new Aliquota();
				
				aliquota.setIdAliquota(rsAliquota.getInt("id"));
				aliquota.setPorcentagem(rsAliquota.getDouble("porcentagem"));
				aliquota.setReduzido(rsAliquota.getDouble("reduzido"));
				aliquota.setPorcentagemFcp(rsAliquota.getDouble("porcentagemfcp"));
				aliquota.setPercentualIcmsDesonerado(rsAliquota.getDouble("percentualicmsdesonerado"));
				aliquota.setSituacaoTributaria(rsAliquota.getInt("situacaotributaria"));
				aliquota.setIdAliquotaPdv(rsAliquota.getObject("idaliquotapdv", Integer.class));
				

				aliquotas.add(aliquota);
				
			}
			return aliquotas;			
			
		} catch (SQLException ex) {
			Log.info("Erro ao recuperar as aliquotas. Erro: " + ex.getMessage());
			throw new RuntimeException("Erro ao recuperar as aliquotas. Erro: " + ex.getMessage());
			
		}

	}
	
	public List<Aliquota> corrigirDescricaoFinal(List<Aliquota> aliquotas) {
		aliquotas.forEach(aliquota -> aliquota.corrigeDescricao());
		
		return aliquotas;
	}
	
	
	public void atualizaAliquota(List<Aliquota> aliquotas) {
		try (Connection conexao = Conexao.conectar();
		     Statement statement = conexao.createStatement()) {

			for(Aliquota aliquota : aliquotas) {
				if(aliquota.isNaoAlteraAliquota()) {
					Log.info("Aliquota de id = " + aliquota.getIdAliquota() + " NAO alterada.");

				}
				else {
					statement.executeUpdate("UPDATE aliquota SET descricao = " + "'" + aliquota.getDescricaoFinal() + "'" + " WHERE id = " + aliquota.getIdAliquota());
					Log.info("Aliquota de id = " + aliquota.getIdAliquota() + " alterada para: " + aliquota.getDescricaoFinal());
				}
			}
			Log.info("Aliquotas alteradas com sucesso.");
		} 	
		catch (SQLException e) {
		e.printStackTrace();
		Log.info(e.getMessage());
		}

	}
}	


