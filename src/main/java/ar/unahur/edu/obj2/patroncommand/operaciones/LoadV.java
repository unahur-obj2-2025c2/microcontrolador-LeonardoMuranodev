package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class LoadV extends Comando{

    private Integer val;

    

    public LoadV(Integer val) {
        this.val = val;
    }



    @Override
    protected void doExecute(Programable micro) {
        micro.setAcumuladorA(val);
    }
    
}
