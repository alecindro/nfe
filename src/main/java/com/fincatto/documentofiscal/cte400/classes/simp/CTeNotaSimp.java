package com.fincatto.documentofiscal.cte400.classes.simp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.cte.CTeConfig;

@Root(name = "CTeSimp")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaSimp {

	@Element(name = "infCte")
	private CTeNotaInfo info;

	public CTeNotaInfo getInfo() {
		return info;
	}

	public void setInfo(CTeNotaInfo info) {
		this.info = info;
	}

}
