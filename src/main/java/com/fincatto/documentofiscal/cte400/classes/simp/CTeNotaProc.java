package com.fincatto.documentofiscal.cte400.classes.simp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.cte.CTeConfig;

@Root(name = "cteProc")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaProc {

	@Element(name = "CTeSimp")
	private CTeNotaSimp cTeNotaSimp;

	public CTeNotaSimp getcTeNotaSimp() {
		return cTeNotaSimp;
	}

	public void setcTeNotaSimp(CTeNotaSimp cTeNotaSimp) {
		this.cTeNotaSimp = cTeNotaSimp;
	}
	
	

}
