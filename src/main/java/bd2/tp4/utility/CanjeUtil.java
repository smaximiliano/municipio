package bd2.tp4.utility;

import java.sql.Date;

public class CanjeUtil{
  public static Date fechaUltimoCanje;

  public static void setFechaUltimoCanje(Date fecha){
    fechaUltimoCanje = fecha;
  }

  public static Date getFechaUltimoCanje(){
    if (fechaUltimoCanje == null)
      throw new RuntimeException(
      "Imposible obtener la fecha del ultimo canje creado: No se ha creado ninguno durante esta ejecucion del programa"
      );
    return fechaUltimoCanje;
  }

}
