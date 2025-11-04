package com.fincatto.documentofiscal.cte400.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;

@Root(name = "gIBSUF")
@Namespace(reference = CTeConfig.NAMESPACE)
public class GIBSUF extends DFBase {

	private static final long serialVersionUID = 1L;

	@Element(name = "pIBSUF", required = true)
	private String pIBSUF;

	@Element(required = false)
	private CTeTDifIBS gDif;

	@Element(required = false)
	private CTeTDevTrib gDevTrib;

	@Element(required = false)
	private CTeTRed gRed;

	@Element(name = "vIBSUF", required = true)
	private String vIBSUF;

	public String getPIBSUF() {
		return pIBSUF;
	}

	public void setPIBSUF(BigDecimal pIBSUF) {
		this.pIBSUF = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pIBSUF, "Alíquota do IBS Estadual ");
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

	public String getVIBSUF() {
		return vIBSUF;
	}

	public void setVIBSUF(BigDecimal vIBSUF) {
		this.vIBSUF = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vIBSUF,
				"Valor do IBS de competência da UF ");
	}

}