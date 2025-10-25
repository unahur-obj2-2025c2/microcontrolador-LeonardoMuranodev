package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Load extends Comando {
    private Integer addr;

    public Load(Integer addr) {
        this.addr = addr;
    }

    @Override
    protected void doExecute(Programable micro) {
        micro.setAcumuladorA(micro.getAddr(addr));
    }
}
