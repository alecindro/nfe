package com.fincatto.documentofiscal.cte400.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;

@Root(name = "gIBSMun")
@Namespace(reference = CTeConfig.NAMESPACE)
public class GIBSMun extends DFBase {

	private static final long serialVersionUID = 1L;

	@Element(name = "pIBSMun", required = true)
	private String pIBSMun;

	@Element(required = false)
	private CTeTDifIBS gDif;

	@Element(required = false)
	private CTeTDevTrib gDevTrib;

	@Element(required = false)
	private CTeTRed gRed;

	@Element(name = "vIBSMun", required = true)
	private String vIBSMun;

	public String getPIBSMun() {
		return pIBSMun;
	}

	public void setPIBSMun(BigDecimal pIBSMun) {
		this.pIBSMun = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pIBSMun, "Alíquota do IBS Municipal");
	}

	public CTeTDifIBS getGDif() {
		return gDif;
	}

	public void setGDif(CTeTDifIBS gDif) {
		this.gDif = gDif;
	}

	public CTeTDevTrib getGDevTrib() {
		return gDevTrib;
	}

	public void setGDevTrib(CTeTDevTrib gDevTrib) {
		this.gDevTrib = gDevTrib;
	}

	public CTeTRed getGRed() {
		return gRed;
	}

	public void setGRed(CTeTRed gRed) {
		this.gRed = gRed;
	}

	public String getVIBSMun() {
		return vIBSMun;
	}

	public void setVIBSMun(BigDecimal vIBSMun) {
		this.vIBSMun = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSMun,
				"Valor do IBS de competência do município");
	}

}

