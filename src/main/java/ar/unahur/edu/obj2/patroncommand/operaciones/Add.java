package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Add extends Comando{

    @Override
    public void doExecute(Programable micro) {
        micro.setAcumuladorA(micro.getAcumuladorA() + micro.getAcumuladorB());
        micro.setAcumuladorB(0);
    }

}
