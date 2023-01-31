package com.fincatto.documentofiscal.cteOSProc300.classes.nota;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;

/**
 * Created by Eldevan Nery Junior on 09/10/17.
 */
@Root(name = "cteOSProc")
public class CTeOSProcessado300 extends DFBase {
    private static final long serialVersionUID = 7518732714448342954L;
   
    @Attribute(name = "versao")
    private String versao;
    
    @Element(name = "CTeOS")
    private CTeNota cteNota;
   
    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

	public CTeNota getCteNota() {
		return cteNota;
	}

	public void setCteNota(CTeNota cteNota) {
		this.cteNota = cteNota;
	}

   
}
