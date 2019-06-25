package Modelo;

import Clases.Distrito;
import Clases.Servicio;
import Utils.Conexion;
import static Utils.Conexion.conectar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class M_Servicios extends Conexion{
    
    //Listar los servicios para llenar la tabla
    public List<Servicio> ListarServicios() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        Servicio servicio;
        try {
            cn = conectar();
            String sql = "{call SP_Read_Servicio}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                servicio = new Servicio();
                servicio.setId(rs.getString(1));
                servicio.setNombre(rs.getString(2));
                servicio.setDescripcion(rs.getString(3));
                servicio.setRecuperarImagen(rs.getBytes(4));
                servicio.setCosto(rs.getFloat(5));
                servicio.setDisponibilidad(rs.getInt(6));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
        return servicios;
    }
    //Recuperar la imagen de cada servicio
    public Servicio RecuperarImagen(Servicio servicio) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Imagen_Servicio (?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1,servicio.getId());
            rs = cs.executeQuery();
            while(rs.next()){
                servicio = new Servicio();
                servicio.setRecuperarImagen(rs.getBytes(1));
            }
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
        return servicio;
    }
    
    //Recuperar un servicio en especifico
    public Servicio RecuperarServicio(Servicio servicio) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Single_Servicio (?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1,servicio.getId());
            rs = cs.executeQuery();
            while(rs.next()){
                servicio = new Servicio();
                servicio.setId(rs.getString(1));
                servicio.setNombre(rs.getString(2));
                servicio.setDescripcion(rs.getString(3));
                servicio.setRecuperarImagen(rs.getBytes(4));
                servicio.setCosto(rs.getFloat(5));
                servicio.setDisponibilidad(rs.getInt(6));
            }
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
        return servicio;
    }
    
    //Agregar un servicio
    public Boolean AgregarServicios(Servicio servicio) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Create_Servicio(?,?,?,?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1,servicio.getNombre());
            cs.setString(2,servicio.getDescripcion());
            cs.setFloat(3,servicio.getCosto());
            cs.setBlob(4,servicio.getGuardarImagen());
            cs.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
        return true;
    }
    //Modificar un servicio
    public Boolean ModificarServicio(Servicio servicio) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Update_Servicio(?,?,?,?,?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1,servicio.getId());
            cs.setString(2,servicio.getNombre());
            cs.setString(3,servicio.getDescripcion());
            cs.setFloat(4,servicio.getCosto());
            cs.setBlob(5,servicio.getGuardarImagen());
            cs.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
        return true;
    }
    
    //Inhabilitar - Habilitar un servicio
    public Boolean InhabilitarHabilitar(Servicio servicio) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Disponibilidad_Servicio (?,?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1,servicio.getId());
            cs.setInt(2,servicio.getDisponibilidad());
            cs.execute();
        return true;
        } catch (SQLException e) {
            return false;
        } finally {
            cs.close();
            cn.close();
        }
    }
}
