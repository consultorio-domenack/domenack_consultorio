package Modelo;

import Clases.Distrito;
import Clases.Perfil;
import Utils.Conexion;
import java.sql.SQLException;

public class M_Login extends Conexion{
    public void iniciarSesion(Perfil perfil) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Iniciar_Sesion(?,?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, perfil.getUsuario().getDni());
            cs.setString(2, perfil.getUsuario().getClave());
            rs = cs.executeQuery();
            if(rs.next()){
                //datos del usuario
                perfil.getUsuario().setId(rs.getString(1));
                perfil.getUsuario().setNivel(rs.getString(2));
                perfil.getUsuario().setCorreo(rs.getString(3));
                //datos del perfil del usuario
                perfil.setNombre(rs.getString(4));
                perfil.setApellidoPaterno(rs.getString(5));
                perfil.setApellidoMaterno(rs.getString(6));
                perfil.setFechaNacimiento(rs.getDate(7));
                perfil.setSexo(rs.getString(8));
                perfil.setEstadoCivil(rs.getString(9));
                perfil.setTelefono(rs.getString(10));
                perfil.setCelular(rs.getString(11));
                perfil.setDireccion(rs.getString(12));
                //datos del distrito del perfil de usuario
                Distrito distrito = new Distrito();
                distrito.setId(rs.getString(13));
                distrito.setDistrito(rs.getString(14));
                //agregar datos del usuario al perfil
                perfil.setDistrito(distrito);
            }
        } catch (SQLException e) {
        } finally {
            cs.close();
            cn.close();
        }
    }
    public String crearUsuario(Perfil perfil) throws SQLException {
        try {
            cn = conectar();
            String sql = "{call SP_Crear_Usuario(?,?,?,?,?,?,?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, perfil.getUsuario().getDni());
            cs.setString(2, perfil.getUsuario().getClave());
            cs.setString(3, perfil.getUsuario().getCorreo());
            cs.setString(4, perfil.getNombre());
            cs.setString(5, perfil.getApellidoPaterno());
            cs.setString(6, perfil.getApellidoMaterno());
            cs.setDate(7, perfil.getFechaNacimiento());
            cs.execute();
        } catch (SQLException e) {
            return ""+e;
        } finally {
            cs.close();
            cn.close();
        }
        return "";
    }
}
