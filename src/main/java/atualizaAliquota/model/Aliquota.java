package atualizaAliquota.model;

import java.lang.String;

public class Aliquota {

	private int idAliquota;
	private double porcentagem;
	private double reduzido;
	private double porcentagemFcp;
	private double percentualIcmsDesonerado;
	private int situacaoTributaria;
	private boolean naoAlteraAliquota;
	private Integer idAliquotaPdv;
	
	private String descricaoFinal;
	
	public String getDescricaoFinal() {
		return descricaoFinal;
	}
	public void setDescricaoFinal(String descricaoFinal) {
		this.descricaoFinal = descricaoFinal;
	}
	public int getIdAliquota() {
		return idAliquota;
	}
	public void setIdAliquota(int idAliquota) {
		this.idAliquota = idAliquota;
	}
	public double getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}
	public double getReduzido() {
		return reduzido;
	}
	public void setReduzido(double reduzido) {
		this.reduzido = reduzido;
	}
	public double getPorcentagemFcp() {
		return porcentagemFcp;
	}
	public void setPorcentagemFcp(double porcentagemFcp) {
		this.porcentagemFcp = porcentagemFcp;
	}
	public double getPercentualIcmsDesonerado() {
		return percentualIcmsDesonerado;
	}
	public void setPercentualIcmsDesonerado(double percentualIcmsDesonerado) {
		this.percentualIcmsDesonerado = percentualIcmsDesonerado;
	}
	public int getSituacaoTributaria() {
		return situacaoTributaria;
	}
	public void setSituacaoTributaria(int situacaoTributaria) {
		this.situacaoTributaria = situacaoTributaria;
	}
	public boolean isNaoAlteraAliquota() {
		return naoAlteraAliquota;
	}
	public void setNaoAlteraAliquota(boolean naoAlteraAliquota) {
		this.naoAlteraAliquota = naoAlteraAliquota;
	}
	public Integer getIdAliquotaPdv() {
		return idAliquotaPdv;
	}
	public void setIdAliquotaPdv(Integer idAliquotaPdv) {
		this.idAliquotaPdv = idAliquotaPdv;
	}
	public void corrigeDescricao() {
		final String SEM_ALIQUOTA_PDV = "(E)";
		String semAliquotaPdvTemp = "";
		String reduzidoTemp = "";
		String fcpTemp = "";
		String desoneradoTemp = "";
		String porcentagemTemp = Double.toString(porcentagem) + "%";	
		
		if(idAliquotaPdv == null) {
			semAliquotaPdvTemp = SEM_ALIQUOTA_PDV;
		}
		
		if (porcentagemTemp.endsWith(".0")) {
            // Remove a parte decimal se for .00
			porcentagemTemp = porcentagemTemp.substring(0, porcentagemTemp.length() - 2);
        }
		
		if(reduzido > 0) {
			reduzidoTemp = Double.toString(reduzido);
			if (reduzidoTemp.endsWith(".0")) {
                // remove a parte decimal se for .00
				reduzidoTemp = reduzidoTemp.substring(0, reduzidoTemp.length() - 2);
            }
			reduzidoTemp = "R" + reduzidoTemp + "%";
		}
		if (porcentagemFcp > 0) {
			fcpTemp = Double.toString(porcentagemFcp);
			if (fcpTemp.endsWith(".0")) {
                // remove a parte decimal se for .00
				fcpTemp = fcpTemp.substring(0, fcpTemp.length() - 2);
            }
			fcpTemp = fcpTemp + "F";
		}
		if(getPercentualIcmsDesonerado() > 0) {
			desoneradoTemp = Double.toString(percentualIcmsDesonerado);
			if (desoneradoTemp.endsWith(".0")) {
                // remove a parte decimal se for .00
				desoneradoTemp = desoneradoTemp.substring(0, desoneradoTemp.length() - 2) + "%";
            }
			desoneradoTemp = "D" +  desoneradoTemp;
		}
		if (porcentagem == 0.0 && reduzido == 100.0 && getSituacaoTributaria() == 40) {
			//se for isenta
			setDescricaoFinal(semAliquotaPdvTemp + "ISENTO " + desoneradoTemp);	
			setNaoAlteraAliquota(false);

		}
		else if (porcentagem == 0.0  && reduzido <= 100.0) {
			//caso seja porcentagem 0 mas nao seja isenta, nao é alterada
			setNaoAlteraAliquota(true);
		}
		else {
			setDescricaoFinal(semAliquotaPdvTemp + porcentagemTemp + reduzidoTemp + " " +  fcpTemp + " " + desoneradoTemp);
			setNaoAlteraAliquota(false);
		}
	}
	
	@Override
	public String toString() {
		return "Aliquota [idAliquota=" + idAliquota + ", porcentagem=" + porcentagem + ", reduzido=" + reduzido
				+ ", porcentagemFcp=" + porcentagemFcp + ", percentualIcmsDesonerado=" + percentualIcmsDesonerado
				+ ", situacaoTributaria=" + situacaoTributaria + ", descricaoFinal=" + descricaoFinal + "]";
	}
	
	
	
	
}
