package Modelo;

import Clases.Cita;
import Clases.ProgramacionCita;
import Utils.Conexion;
import static Utils.Conexion.conectar;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class M_Citas extends Conexion {

    public List<ProgramacionCita> recuperarHorario() throws SQLException {
        List<ProgramacionCita> programaciones = new ArrayList<>();
        ProgramacionCita programacion;
        try {
            cn = conectar();
            String sql = "{call SP_Horario}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                programacion = new ProgramacionCita();
                Timestamp timestamp = rs.getTimestamp(1);
                Date date = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());
                programacion.setFecha(date);
                programacion.setHora_ini_valor(rs.getTime(2));
                programacion.setHora_fin_valor(rs.getTime(3));
                programacion.setEstado(rs.getString(4));
                programaciones.add(programacion);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return programaciones;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return programaciones;
    }

    public List<ProgramacionCita> recuperarHorarioSemana(Date diaInicial, Date diaFinal) throws SQLException {
        List<ProgramacionCita> programaciones = new ArrayList<>();
        ProgramacionCita programacion;
        try {
            cn = conectar();
            String sql = "{call SP_Horario_Semana (?,?)}";
            cs = cn.prepareCall(sql);
            cs.setDate(1, diaInicial);
            cs.setDate(2, diaFinal);
            rs = cs.executeQuery();
            while (rs.next()) {
                programacion = new ProgramacionCita();
                Timestamp timestamp = rs.getTimestamp(1);
                Date date = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());
                programacion.setFecha(date);
                programacion.setHora_ini_valor(rs.getTime(2));
                programacion.setHora_fin_valor(rs.getTime(3));
                programacion.setEstado(rs.getString(4));
                programacion.setId(rs.getInt(5));
                programaciones.add(programacion);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return programaciones;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return programaciones;
    }

    public Boolean generarCita(Cita cita) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Reservar_Cita(?,?,?)}";
            cs = cn.prepareCall(sql);
            cs.setInt(1, cita.getProgramacionCita().getId());
            cs.setString(2, cita.getPerfilUsuario().getUsuario().getId());
            cs.setString(3, cita.getServicio().getId());
            cs.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
        return true;
    }

    public List<Cita> recuperarCitas(Date dia) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        Cita cita;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Citas_Dia (?)}";
            cs = cn.prepareCall(sql);
            cs.setDate(1, dia);
            rs = cs.executeQuery();
            while (rs.next()) {
                cita = new Cita();
                cita.getPerfilUsuario().setNombre(rs.getString(1));
                cita.getPerfilUsuario().getUsuario().setDni(rs.getString(2));
                cita.getServicio().setNombre(rs.getString(3));
                cita.getProgramacionCita().setHora_ini_valor(rs.getTime(4));
                cita.getProgramacionCita().setHora_fin_valor(rs.getTime(5));
                cita.setId(rs.getInt(6));
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return citas;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return citas;
    }

    public boolean verificarProgramacion(Date dia) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Verificar_Programacion (?)}";
            cs = cn.prepareCall(sql);
            cs.setDate(1, dia);
            rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            return false;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return true;
    }
    public Date recuperarUltimaFecha() throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Recuperar_Dia_Final}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return null;
    }
    public boolean AtenderCita(Cita cita) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Atender_Cita (?)}";
            cs = cn.prepareCall(sql);
            cs.setInt(1, cita.getId());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
    }
    
    public Boolean crearHorario(ProgramacionCita programacion) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_CREARHORARIOMENSUAL(?,?,?)}";
            cs = cn.prepareCall(sql);
            //Ingresar dia
            cs.setDate(1, programacion.getFecha());
            //Recorrer horas de inicio y fin

            List<Time> tiempo = programacion.getHora_ini();

            for (int i = 0; i < tiempo.size(); i++) {
                cs.setTime(2, programacion.getHora_ini().get(i));
                cs.setTime(3, programacion.getHora_fin().get(i));
                cs.execute();
            }
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
        return true;
    }
    public List<Cita> recuperarCitasAtendidas(Date dia) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        Cita cita;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Citas_Atendidas (?)}";
            cs = cn.prepareCall(sql);
            cs.setDate(1, dia);
            rs = cs.executeQuery();
            while (rs.next()) {
                cita = new Cita();
                cita.getPerfilUsuario().setNombre(rs.getString(1));
                cita.getPerfilUsuario().getUsuario().setDni(rs.getString(2));
                cita.getServicio().setNombre(rs.getString(3));
                cita.getProgramacionCita().setHora_ini_valor(rs.getTime(4));
                cita.getProgramacionCita().setHora_fin_valor(rs.getTime(5));
                cita.setId(rs.getInt(6));
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return citas;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return citas;
    }
}
