package com.fincatto.documentofiscal.cte400.classes.nota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte.CTeConfig;

/**
 * Implementação Nota Técnica 2025.001 – Reforma Tributária do Consumo, Versão
 * 1.05 de 06 de junho de 2025
 *
 * 15/06/2025
 *
 * @author Edivaldo Merlo Stens
 */
@Root(name = "IBSCBS")
@Namespace(reference = CTeConfig.NAMESPACE)
public class CTeNotaInfoInformacoesRelativasImpostosIBSCBS extends DFBase {

  private static final long serialVersionUID = -3330020091021955690L;

  @Element(name = "CST", required = true)
  private String cst;

  @Element(name = "cClassTrib", required = true)
  private String cClassTrib;

  @Element(name = "gIBSCBS", required = false)
  private TCIBS gIBSCBS;

  public String getCST() {
    return cst;
  }

  public void setCST(String cst) {
    this.cst = cst;
  }

  public String getCClassTrib() {
    return cClassTrib;
  }

  public void setCClassTrib(String cClassTrib) {
    this.cClassTrib = cClassTrib;
  }

  public TCIBS getGIBSCBS() {
    return gIBSCBS;
  }

  public void setGIBSCBS(TCIBS gIBSCBS) {
    this.gIBSCBS = gIBSCBS;
  }

}
