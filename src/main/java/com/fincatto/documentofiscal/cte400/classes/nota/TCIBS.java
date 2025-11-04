package com.fincatto.documentofiscal.cte400.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;

@Root(name = "gIBSCBS")
@Namespace(reference = CTeConfig.NAMESPACE)
public class TCIBS extends DFBase {

	private static final long serialVersionUID = 6387739393518311269L;

	@Element(name = "vBC", required = true)
	private String vBC;

	@Element(name = "vIBS")
	private String vIBS;

	@Element(name = "gIBSUF", required = true)
	private GIBSUF gIBSUF;

	@Element(name = "gIBSMun", required = true)
	private GIBSMun gIBSMun;

	@Element(name = "gCBS", required = true)
	private GCBS gCBS;

	@Element(required = false)
	private CTeTTribRegular gTribRegular;

	@Element(name = "gIBSCredPres",required = false)
	private CTeTCredPres gIBSCredPres;

	@Element(name = "gCBSCredPres",required = false)
	private CTeTCredPres gCBSCredPres;

	@Element(required = false)
	private CTeTTribCompraGov gTribCompraGov;

	public String getVBC() {
		return vBC;
	}

	public void setVBC(BigDecimal vBC) {
		this.vBC = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vBC, "Valor da Base de cálculo comum a IBS/CBS");
	}

	public String getvIBS() {
		return vIBS;
	}

	public void setvIBS(String vIBS) {
		this.vIBS = vIBS;
	}

	public GIBSUF getGIBSUF() {
		return gIBSUF;
	}

	public void setGIBSUF(GIBSUF gIBSUF) {
		this.gIBSUF = gIBSUF;
	}

	public GIBSMun getGIBSMun() {
		return gIBSMun;
	}

	public void setGIBSMun(GIBSMun gIBSMun) {
		this.gIBSMun = gIBSMun;
	}

	public GCBS getGCBS() {
		return gCBS;
	}

	public void setGCBS(GCBS gCBS) {
		this.gCBS = gCBS;
	}

	public CTeTTribRegular getGTribRegular() {
		return gTribRegular;
	}

	public void setGTribRegular(CTeTTribRegular gTribRegular) {
		this.gTribRegular = gTribRegular;
	}

	public CTeTCredPres getGIBSCredPres() {
		return gIBSCredPres;
	}

	public void setGIBSCredPres(CTeTCredPres gIBSCredPres) {
		this.gIBSCredPres = gIBSCredPres;
	}

	public CTeTCredPres getGCBSCredPres() {
		return gCBSCredPres;
	}

	public void setGCBSCredPres(CTeTCredPres gCBSCredPres) {
		this.gCBSCredPres = gCBSCredPres;
	}

	public CTeTTribCompraGov getGTribCompraGov() {
		return gTribCompraGov;
	}

	public void setGTribCompraGov(CTeTTribCompraGov gTribCompraGov) {
		this.gTribCompraGov = gTribCompraGov;
	}



	
}
