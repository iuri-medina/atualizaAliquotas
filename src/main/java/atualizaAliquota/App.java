package atualizaAliquota;

import java.util.List;
import atualizaAliquota.dao.AliquotaDAO;
import atualizaAliquota.model.Aliquota;
import atualizaAliquota.util.Log;


public class App {

	public static void main(String[] args) {
		
		Log.init();

		AliquotaDAO aliquotaDao = new AliquotaDAO();
		
		List<Aliquota> aliquotas = aliquotaDao.getAliquotaParaCorrecao();
		
		List<Aliquota> aliquotasCorrigidas = aliquotaDao.corrigirDescricaoFinal(aliquotas);
		
		aliquotaDao.atualizaAliquota(aliquotasCorrigidas);
		
		
	}

}
