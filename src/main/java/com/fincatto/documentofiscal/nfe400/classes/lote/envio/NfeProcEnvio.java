package com.fincatto.documentofiscal.nfe400.classes.lote.envio;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nfeProc")
public class NfeProcEnvio {

	@Element(name = "enviNFe")
	private NFLoteEnvio nfLoteEnvio;

	public NFLoteEnvio getNfLoteEnvio() {
		return nfLoteEnvio;
	}

	public void setNfLoteEnvio(NFLoteEnvio nfLoteEnvio) {
		this.nfLoteEnvio = nfLoteEnvio;
	}
	
	
}
