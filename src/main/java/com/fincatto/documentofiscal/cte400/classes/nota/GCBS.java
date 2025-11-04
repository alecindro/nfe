package com.fincatto.documentofiscal.cte400.classes.nota;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;

@Root(name = "gCBS")
@Namespace(reference = CTeConfig.NAMESPACE)
public class GCBS extends DFBase {

	private static final long serialVersionUID = 1L;

	@Element(name = "pCBS", required = true)
	private String pCBS;

	@Element(required = false)
	private CTeTDifCBS gDif;

	@Element(required = false)
	private CTeTDevTrib gDevTrib;

	@Element(required = false)
	private CTeTRed gRed;

	@Element(name = "vCBS", required = true)
	private String vCBS;

	public String getPCBS() {
		return pCBS;
	}

	public void setPCBS(BigDecimal pCBS) {
		this.pCBS = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(pCBS, "Alíquota da CBS");
	}

	public CTeTDifCBS getGDif() {
		return gDif;
	}

	public void setGDif(CTeTDifCBS gDif) {
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

	public String getVCBS() {
		return vCBS;
	}

	public void setVCBS(BigDecimal vCBS) {
		this.vCBS = DFBigDecimalValidador.tamanho13Com2CasasDecimais(vCBS, "Valor da CBS");
	}
}