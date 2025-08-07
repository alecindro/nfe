package com.fincatto.documentofiscal.cte400.transformers;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.cte400.classes.CTModalAquavTpNav;

public class CTModalAquavTpNavTransformer implements Transform<CTModalAquavTpNav> {
    
    @Override
    public CTModalAquavTpNav read(String arg0) {
        return CTModalAquavTpNav.valueOfCodigo(arg0);
    }
    
    @Override
    public String write(CTModalAquavTpNav arg0) {
        return arg0.getCodigo();
    }
}