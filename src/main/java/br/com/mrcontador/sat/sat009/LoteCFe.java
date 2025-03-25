package br.com.mrcontador.sat.sat009;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoteCFe  implements Serializable
{

    private final static long serialVersionUID = -1L;
    @JsonProperty(value = "CFe", required = true)
    protected List<EnvCFe.LoteCFe.CFe> cves;


    public List<EnvCFe.LoteCFe.CFe> getCves() {
        if (cves == null) {
            cves = new ArrayList<EnvCFe.LoteCFe.CFe>();
        }
        return this.cves;
    }

}
