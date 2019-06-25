package Modelo;

import Clases.Cita;
import Clases.Perfil;
import Clases.Usuario;
import Utils.Conexion;
import static Utils.Conexion.conectar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class M_Usuario extends Conexion{
    public List<Perfil> ListarUsuarios() throws SQLException {
        List<Perfil> perfiles = new ArrayList<>();
        Perfil perfil;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Lista_Usuarios}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                perfil = new Perfil();
                perfil.getUsuario().setId(rs.getString(1));
                perfil.getUsuario().setDni(rs.getString(2));
                perfil.getUsuario().setCorreo(rs.getString(3));
                perfil.setNombre(rs.getString(4));
                perfil.setApellidoPaterno(rs.getString(5));
                perfil.setApellidoMaterno(rs.getString(6));
                perfil.setFechaNacimiento(rs.getDate(7));
                perfil.setSexo(rs.getString(8));
                perfil.setEstadoCivil(rs.getString(9));
                perfil.setTelefono(rs.getString(10));
                perfil.setCelular(rs.getString(11));
                perfil.setDireccion(rs.getString(12));
                perfiles.add(perfil);
            }
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
        return perfiles;
    }
    
    public Perfil RecuperarUsuario(Perfil perfil) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Usuario (?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, perfil.getUsuario().getId());
            rs = cs.executeQuery();
            while(rs.next()){
                perfil = new Perfil();
                perfil.getUsuario().setId(rs.getString(1));
                perfil.getUsuario().setDni(rs.getString(2));
                perfil.getUsuario().setCorreo(rs.getString(3));
                perfil.setNombre(rs.getString(4));
                perfil.setApellidoPaterno(rs.getString(5));
                perfil.setApellidoMaterno(rs.getString(6));
                perfil.setFechaNacimiento(rs.getDate(7));
                perfil.setSexo(rs.getString(8));
                perfil.setEstadoCivil(rs.getString(9));
                perfil.setTelefono(rs.getString(10));
                perfil.setCelular(rs.getString(11));
                perfil.setDireccion(rs.getString(12));
            }
            return perfil;
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
        return perfil;
    }
    public List<Cita> HistorialCitas(Perfil usuario) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        Cita cita;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Historial_Citas (?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, usuario.getUsuario().getId());
            rs = cs.executeQuery();
            while (rs.next()) {
                cita = new Cita();
                cita.getServicio().setNombre(rs.getString(1));
                cita.getProgramacionCita().setFecha(rs.getDate(2));
                cita.getProgramacionCita().setHora_ini_valor(rs.getTime(3));
                cita.getServicio().setCosto(rs.getFloat(4));
                citas.add(cita);
            }
        } catch (SQLException e) {
            return citas;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return citas;
    }
    public List<Cita> HistorialCitasPendientes(Perfil usuario) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        Cita cita;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Citas_Pendientes (?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, usuario.getUsuario().getId());
            rs = cs.executeQuery();
            while (rs.next()) {
                cita = new Cita();
                cita.getServicio().setNombre(rs.getString(1));
                cita.getProgramacionCita().setFecha(rs.getDate(2));
                cita.getProgramacionCita().setHora_ini_valor(rs.getTime(3));
                cita.getServicio().setCosto(rs.getFloat(4));
                citas.add(cita);
            }
        } catch (SQLException e) {
            return citas;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return citas;
    }
}
