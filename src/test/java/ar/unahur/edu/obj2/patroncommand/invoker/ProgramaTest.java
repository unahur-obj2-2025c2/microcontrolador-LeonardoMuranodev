package ar.unahur.edu.obj2.patroncommand.invoker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.*;

public class ProgramaTest {

    private Programa p = new Programa();
    private Programable micro = new Microcontrolador();

    @BeforeEach
    void setUp() {
        p.vaciarOperaciones();
        p.reset(micro);
    }

    void ejecutar() {
        p.ejecutar(micro);
    }

    @Test
    void avanzarTresPosicionesEnPogramCounter() {
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());

        //Accion
        this.ejecutar();

        //Chequeo
        assertEquals(3, micro.getProgramCounter());
    }

    @Test
    void sumar20Mas17YObtener37EnAcumA() {
        //Preparacion
        p.agregarOperacion(new LoadV(20));
        p.agregarOperacion(new Swap());
        p.agregarOperacion(new LoadV(17));
        p.agregarOperacion(new Add());

        //Accion
        this.ejecutar();

        //Chequeo
        assertEquals(4, micro.getProgramCounter());
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }

    @Test
    void sumar8Mas5Mas2YObtener37EnAcumA() {
        //Preparacion
        p.agregarOperacion(new LoadV(2)); // -> Carga 2 en A
        p.agregarOperacion(new Str(0)); // -> Guarda en la pos 0, el 2
        p.agregarOperacion(new LoadV(8)); // -> Carga 8 en A
        p.agregarOperacion(new Swap()); // -> Invierte lo vlaores: A en 0, 8 en B
        p.agregarOperacion(new LoadV(5)); // -> Carga 5 en A
        p.agregarOperacion(new Add()); // -> Suma los valores: en A queda 13, y 0 en B
        p.agregarOperacion(new Swap()); // -> Invierte los valores: 13 queda en B y A queda en 0
        p.agregarOperacion(new Load(0)); // -> Carga en A, el valor de la pos 0 de memoria: 2
        p.agregarOperacion(new Add()); // -> Suma 2 con 13 y queda el valor en A

        //Accion
        this.ejecutar();

        //Chequeo
        assertEquals(9, micro.getProgramCounter());
        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }
}
