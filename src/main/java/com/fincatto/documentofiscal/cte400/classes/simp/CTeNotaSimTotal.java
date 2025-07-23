package com.fincatto.documentofiscal.cte400.classes.simp;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;

@Root(name = "total")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaSimTotal extends DFBase {
    private static final long serialVersionUID = -5913703822180633261L;
    
    @Element(name = "vTPrest",required = false)
    private String valorTotalPrestacaoServico;
    
    @Element(name = "vTRec")
    private String valorReceber;

    @Element(name = "vTotDFe", required = false)
    private String valorTotalFiscal;


    public String getValorTotalPrestacaoServico() {
        return this.valorTotalPrestacaoServico;
    }

    /**
     * Valor Total da Prestação do Serviço<br>
     * Pode conter zeros quando o CT-e for de complemento de ICMS
     */
    public void setValorTotalPrestacaoServico(final BigDecimal valorTotalPrestacaoServico) {
        this.valorTotalPrestacaoServico = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorTotalPrestacaoServico, "Valor Total da Prestação do Serviço");
    }

    public String getValorReceber() {
        return this.valorReceber;
    }

    /**
     * Valor a Receber
     */
    public void setValorReceber(final BigDecimal valorReceber) {
        this.valorReceber = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorReceber, "Valor a Receber");
    }

	public String getValorTotalFiscal() {
		return valorTotalFiscal;
	}

	public void setValorTotalFiscal(String valorTotalFiscal) {
		this.valorTotalFiscal = valorTotalFiscal;
	}

	public void setValorTotalPrestacaoServico(String valorTotalPrestacaoServico) {
		this.valorTotalPrestacaoServico = valorTotalPrestacaoServico;
	}

	public void setValorReceber(String valorReceber) {
		this.valorReceber = valorReceber;
	}


}
