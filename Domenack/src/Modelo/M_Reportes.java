package Modelo;

import Clases.Perfil;
import Clases.ProgramacionCita;
import Clases.Servicio;
import Utils.Conexion;
import static Utils.Conexion.conectar;
import java.sql.SQLException;
import java.util.List;

public class M_Reportes extends Conexion {
    public List<Servicio> RankingServicios(List<Servicio> servicios) throws SQLException {
        Servicio servicio;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Ranking_Servicio}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                servicio = new Servicio();
                servicio.setCantidad(rs.getInt(1));
                servicio.setNombre(rs.getString(2));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            return servicios;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return servicios;
    }
    
    public List<Servicio> GananciaSegunServicio(List<Servicio> servicios) throws SQLException {
        Servicio servicio;
        try {
            cn = conectar();
            String sql = "{call SP_Ver_Ganancia_Servicio}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                servicio = new Servicio();
                servicio.setGanancia(rs.getInt(1));
                servicio.setNombre(rs.getString(2));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return servicios;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return servicios;
    }
    public List<ProgramacionCita> DiaMasPedido(List<ProgramacionCita> dias) throws SQLException {
        ProgramacionCita dia;
        try {
            cn = conectar();
            String sql = "{call SP_Dia_Mas_Pedido}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                dia = new ProgramacionCita();
                dia.setCantidad(rs.getInt(1));
                dia.setDia(rs.getString(2));
                dias.add(dia);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return dias;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return dias;
    }
    public List<Perfil> CitasPorUsuario(List<Perfil> perfiles) throws SQLException {
        Perfil perfil;
        try {
            cn = conectar();
            String sql = "{call SP_Citas_Por_Usuario}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                perfil = new Perfil();
                perfil.setCantidad(rs.getInt(1));
                perfil.setNombre(rs.getString(2));
                perfil.getUsuario().setDni(rs.getString(3));
                perfiles.add(perfil);
            }
        } catch (SQLException e) {
            System.out.println("error");
            return perfiles;
        } finally {
            rs.close();
            cs.close();
            cn.close();
        }
        return perfiles;
    }
}
