package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.Arrays;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Microcontrolador implements Programable {
    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter;

    private List<Integer> memoria = Arrays.asList(new Integer[1024]);

    public Microcontrolador() {
        this.reset();
    }

    @Override
    public void run(List<Operable> operaciones) {
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void incProgramCounter() {
        programCounter++;
    }

    @Override
    public Integer getProgramCounter() {
        return programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    private void estaDentroDelRangoDeMemoria(Integer direccion) {
        if(direccion < 0 || direccion > memoria.size()) {
            throw new FueraDeRangoDeMemoriaException();
        }
    }

    @Override
    public void setAddr(Integer addr) {
        this.estaDentroDelRangoDeMemoria(addr);
        memoria.set(addr, acumuladorA);
    }

    @Override
    public Integer getAddr(Integer addr) {
        this.estaDentroDelRangoDeMemoria(addr);
        return memoria.get(addr);
    }

    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        programCounter = 0;
        memoria = Arrays.asList(new Integer[1024]);;
    }

    @Override
    public Programable copiar() {
        Microcontrolador nuevo = new Microcontrolador();
        nuevo.acumuladorA = acumuladorA;
        nuevo.acumuladorB = acumuladorB;
        nuevo.programCounter = programCounter;
        return nuevo;
    }

    @Override
    public void copiarDesde(Programable micro) {
        this.acumuladorA = micro.getAcumuladorA();
        this.acumuladorB = micro.getAcumuladorB();
        this.programCounter = micro.getProgramCounter();
    }

    
}
