package atualizaAliquota.model;

import java.lang.String;

public class Aliquota {

	private int idAliquota;
	private double porcentagem;
	private double reduzido;
	private double porcentagemFcp;
	private double percentualIcmsDesonerado;
	
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

	public void corrigeDescricao() {
		
		String reduzidoTemp = "";
		String fcpTemp = "";
		String desoneradoTemp = "";
		String porcentagemTemp = Double.toString(porcentagem);
		
		if (porcentagemTemp.endsWith(".0")) {
            // Remove a parte decimal se for .00
			porcentagemTemp = porcentagemTemp.substring(0, porcentagemTemp.length() - 2);
        }
		
		if(reduzido > 0) {
			reduzidoTemp = Double.toString(reduzido);
			if (reduzidoTemp.endsWith(".0")) {
                // Remove a parte decimal se for .00
				reduzidoTemp = reduzidoTemp.substring(0, reduzidoTemp.length() - 2);
            }
			reduzidoTemp = "R" + reduzidoTemp + "%";
		}
		if (porcentagemFcp > 0) {
			fcpTemp = Double.toString(porcentagemFcp);
			if (fcpTemp.endsWith(".0")) {
                // Remove a parte decimal se for .00
				fcpTemp = fcpTemp.substring(0, fcpTemp.length() - 2);
            }
			fcpTemp = fcpTemp + "F";
		}
		if(getPercentualIcmsDesonerado() > 0) {
			desoneradoTemp = Double.toString(percentualIcmsDesonerado);
			if (desoneradoTemp.endsWith(".0")) {
                // Remove a parte decimal se for .00
				desoneradoTemp = desoneradoTemp.substring(0, desoneradoTemp.length() - 2);
            }
			desoneradoTemp = "D" +  desoneradoTemp;
		}
		setDescricaoFinal(porcentagemTemp + "% " + reduzidoTemp + " " +  fcpTemp + " " + desoneradoTemp);	
	}
	
	
}
