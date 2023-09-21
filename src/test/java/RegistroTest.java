import org.example.Registro;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class RegistroTest {
    int[] edades;
    boolean[] respuestasEsperadasParaEdades;

    String[][] ejemploRegistro;
    int contadorMayoresEdad;
    int contadorMenoresEdad;
    int contadorTerceraEdad;

    @BeforeEach
    public void init(){
        edades = new int[]{130, 0, -20, 15};
        respuestasEsperadasParaEdades = new boolean[]{false, false, false, true};

        ejemploRegistro = new String[][]{
                {"Juan", "Casado", "25"},
                {"María", "Soltera", "30"},
                {"Pedro", "Casado", "17"},
                {"Ana", "Soltera", "20"},
                {"Luis", "Casado", "70"}
        };
        contadorMayoresEdad = 4;
        contadorMenoresEdad = 1;
        contadorTerceraEdad = 1;

    }

    @Test
    public void validarEdadTest(){
        boolean respuestaCorrecta = true;
        for(int i = 0; i < edades.length; i++){
            if(Registro.validarEdad(edades[i]) != respuestasEsperadasParaEdades[i]){
                respuestaCorrecta = false;
            }
        }
        assertTrue(respuestaCorrecta);
    }

    @Test
    public void contarMayoresEdad(){
        boolean contadorCorrecto = true;
        if (Registro.contarMayoresDeEdad(ejemploRegistro) != contadorMayoresEdad){
            contadorCorrecto = false;
        }
        assertTrue(contadorCorrecto);
    }

    @Test
    public void contarMenoresEdad(){
        boolean contadorCorrecto = true;
        if (Registro.contarMenoresDeEdad(ejemploRegistro) != contadorMenoresEdad){
            contadorCorrecto = false;
        }
        assertTrue(contadorCorrecto);
    }

    @Test
    public void contarTerceraEdad(){
        boolean contadorCorrecto = true;
        if (Registro.contarPersonasTerceraEdad(ejemploRegistro) != contadorTerceraEdad){
            contadorCorrecto = false;
        }
        assertTrue(contadorCorrecto);
    }


}

