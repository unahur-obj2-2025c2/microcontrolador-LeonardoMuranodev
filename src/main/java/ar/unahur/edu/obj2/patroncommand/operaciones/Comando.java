package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public abstract class Comando implements Operable{

    Programable microBackup;

    @Override
    public void execute(Programable micro) {
        //Antes de ejecutar, guardamos el estado
        microBackup = micro.copiar(); 
        this.doExecute(micro);
        micro.incProgramCounter();
    }

    protected abstract void doExecute(Programable micro);

    @Override
    public void undo(Programable micro) {
        micro.copiarDesde(microBackup);
    }
}
