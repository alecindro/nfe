package com.fincatto.documentofiscal.cteOSProc300.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte300.classes.nota.CTeNotaInfo;
import com.fincatto.documentofiscal.cte300.classes.nota.CTeNotaInfoSuplementares;
import com.fincatto.documentofiscal.cte300.classes.nota.assinatura.CTeSignature;

/**
 * @author Caio
 * @info Tipo Conhecimento de Transporte Eletrônico (Modelo 57)
 */

@Root(name = "CTeOS")
@Namespace(reference = "http://www.portalfiscal.inf.br/cte")
public class CTeNota extends DFBase {
    private static final long serialVersionUID = -3697915253635990400L;
    
    @Element(name = "infCte")
    private CTeNotaInfo info;

    @Element(name="infCTeSupl", required = false)
    private CTeNotaInfoSuplementares infoSuplementares;

    @Element(name = "Signature", required = false)
    private CTeSignature signature;

    public CTeNota() {
        this.info = null;
        this.signature = null;
    }

    public CTeNotaInfo getCteNotaInfo() {
        return this.info;
    }

    public void setCteNotaInfo(final CTeNotaInfo info) {
        this.info = info;
    }

    public CTeNotaInfoSuplementares getInfoSuplementares() {
        return infoSuplementares;
    }

    public CTeNota setInfoSuplementares(CTeNotaInfoSuplementares infoSuplementares) {
        this.infoSuplementares = infoSuplementares;
        return this;
    }

    public CTeSignature getSignature() {
        return this.signature;
    }

    public void setSignature(final CTeSignature signature) {
        this.signature = signature;
    }
}
