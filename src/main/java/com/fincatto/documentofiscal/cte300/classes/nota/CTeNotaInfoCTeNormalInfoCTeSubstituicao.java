package com.fincatto.documentofiscal.cte300.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @author Caio
 * @info Informações do CT-e de substituição
 */

@Root(name = "infCteSub")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNotaInfoCTeNormalInfoCTeSubstituicao extends DFBase {
    private static final long serialVersionUID = 8643576155858141154L;
    
    @Element(name = "chCte")
    private String chaveCTe;
    
    @Element(name = "refCteAnu", required = false)
    private String chaveCTeAnulacao;
    
    @Element(name = "tomaICMS",required = false)
    private CTeNotaInfoCTeNormalInfoCTeSubstituicaoTomadorICMS tomadorICMS;

    @Element(name = "indAlteraToma", required = false)
    private String indicadorAlteracaoTomador;

    public CTeNotaInfoCTeNormalInfoCTeSubstituicao() {
        this.chaveCTe = null;
        this.chaveCTeAnulacao = null;
        this.tomadorICMS = null;
        this.indicadorAlteracaoTomador = null;
    }

    public String getChaveCTe() {
        return this.chaveCTe;
    }

    /**
     * Chave de acesso do CT-e a ser substituído (original)
     */
    public void setChaveCTe(final String chaveCTe) {
        DFStringValidador.exatamente44N(chaveCTe, "Chave de acesso do CT-e a ser substituído (original)");
        this.chaveCTe = chaveCTe;
    }

    public String getChaveCTeAnulacao() {
        return this.chaveCTeAnulacao;
    }

    /**
     * Chave de acesso do CT-e de Anulação
     */
    public void setChaveCTeAnulacao(final String chaveCTeAnulacao) {
        DFStringValidador.exatamente44N(chaveCTeAnulacao, "Chave de acesso do CT-e de Anulação");
        this.chaveCTeAnulacao = chaveCTeAnulacao;
    }

    public CTeNotaInfoCTeNormalInfoCTeSubstituicaoTomadorICMS getTomadorICMS() {
        return this.tomadorICMS;
    }

    /**
     * Tomador é contribuinte do ICMS, mas não é emitente de documento fiscal eletrônico
     */
    public void setTomadorICMS(final CTeNotaInfoCTeNormalInfoCTeSubstituicaoTomadorICMS tomadorICMS) {
        this.tomadorICMS = tomadorICMS;
    }

    public String getIndicadorAlteracaoTomador() {
        return this.indicadorAlteracaoTomador;
    }

    /**
     * Indicador de CT-e Alteração de Tomador<br>
     * Tag com efeito e utilização aguardando legislação, não utilizar antes de NT específica tratar desse procedimento
     */
    public void setIndicadorAlteracaoTomador(final String indicadorAlteracaoTomador) {
        DFStringValidador.exatamente1(indicadorAlteracaoTomador, "Indicador de CT-e Alteração de Tomador");
        this.indicadorAlteracaoTomador = indicadorAlteracaoTomador;
    }
}
