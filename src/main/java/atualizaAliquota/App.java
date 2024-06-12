package atualizaAliquota;

import java.util.List;
import atualizaAliquota.dao.AliquotaDAO;
import atualizaAliquota.model.Aliquota;


public class App {

	public static void main(String[] args) {

		AliquotaDAO aliquotaDao = new AliquotaDAO();
		
		List<Aliquota> aliquotas = aliquotaDao.getAliquotaParaCorrecao();
		
		List<Aliquota> aliquotasCorrigidas = aliquotaDao.corrigirDescricaoFinal(aliquotas);
		
		aliquotaDao.atualizaAliquota(aliquotasCorrigidas);
		
		
	}

}
