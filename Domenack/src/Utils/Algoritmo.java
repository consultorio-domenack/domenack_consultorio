package Utils;

import Clases.ProgramacionCita;
import Modelo.M_Citas;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Algoritmo {
    
    private static final M_Citas DAO_CITA = new M_Citas();

    public static void generarProgramacionDeCitas(Date diaHoy) {
        //Hora local
        LocalTime valorHora = LocalTime.of(17, 0, 0);
        LocalDate valorDia = diaHoy.toLocalDate();
        //Creacion de las listas
        List<ProgramacionCita> programacionMensual = new ArrayList<>();
        List<Time> horasIniciales;
        List<Time> horasFinales;
        //Elementos de las listas
        ProgramacionCita citaDiaria;
        Date dia;
        Time horaInicio;
        Time horaFinal;
        //Cantidad de dias
        Calendar calendarioMes = Calendar.getInstance();
        calendarioMes.set(valorDia.getYear(), valorDia.getMonth().getValue() - 1, valorDia.getDayOfMonth());
        int numDias = calendarioMes.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(numDias);
        int cantDias = 35;
        System.out.println(cantDias);
        int j = 0;
        while (j < cantDias) {
            int dia_semana = valorDia.getDayOfWeek().getValue();
            if (dia_semana == 6 || dia_semana == 7) {
                valorDia = valorDia.plusDays(1);
                j++;
                continue;
            }
            //Instanciar la programacion, las horas y el dia
            citaDiaria = new ProgramacionCita();
            horasIniciales = new ArrayList<>();
            horasFinales = new ArrayList<>();
            dia = Date.valueOf(valorDia);
            //Fecha
            citaDiaria.setFecha(dia);
            //Separacion
            System.out.println(valorDia);
            valorDia = valorDia.plusDays(1);
            for (int i = 0; i < 9; i++) {
                //Hora de inicio
                horaInicio = Time.valueOf(valorHora);
                horasIniciales.add(horaInicio);
                //Separacion
                valorHora = valorHora.plusMinutes(20);
                //Hora de fin
                horaFinal = Time.valueOf(valorHora);
                horasFinales.add(horaFinal);
            }
            //Rellenar la programacion con las horas correspondientes
            citaDiaria.setHora_ini(horasIniciales);
            citaDiaria.setHora_fin(horasFinales);
            //Resetear las horas
            valorHora = LocalTime.of(17, 0, 0);
            //Agregar a la lista de programación
            programacionMensual.add(citaDiaria);
            //Aumentar iterador
            j++;
        }
        programacionMensual.forEach((programacion) -> {
            try {
                System.out.println(DAO_CITA.crearHorario(programacion));
            } catch (SQLException ex) {
                System.out.println("error");
            }
        });
    }
}
