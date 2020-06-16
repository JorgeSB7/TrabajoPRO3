package com.mycompany.mavenproject1.model;

import com.mycompany.mavenproject1.Utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class SesionDAO extends Sesion {
    
    //Queries
    final String insertar = "INSERT INTO sesion (CS, CD, CF, CD2, CF2, nombre, duracion) VALUES(NULL,?,?,?,?,?,?)";
    final String actualizar = "UPDATE sesion SET CD = ?, CF = ?, CD2 = ?, CF2 = ?, nombre = ?, duracion = ? WHERE CS = ?";
    final String borrar = "DELETE FROM sesion WHERE CS=?";
    final String select = "SELECT * FROM sesion WHERE CS=";

    private boolean persist;

    public SesionDAO() {
        super();
        persist = false;
    }

    public SesionDAO(int CS, int CD, int CF, int CD2, int CF2, String nombre, int duracion) {
        super(CS, CD, CF, CD2, CF2, nombre, duracion);
        persist = false;
    }

    public SesionDAO(int CD, int CF, int CD2, int CF2, String nombre, int duracion) {
        super(-1, CD, CF, CD2, CF2, nombre, duracion);
        persist = false;
    }

    public SesionDAO(Sesion s) {
        CS = s.CS;
        CD = s.CD;
        CF = s.CF;
        CD2 = s.CD2;
        CF2 = s.CF2;
        nombre = s.nombre;
        duracion = s.duracion;
    }

    public SesionDAO(int CS) {
        super();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.CS = CS;
                    this.CD = rs.getInt("CD");
                    this.CF = rs.getInt("CF");
                    this.CD2 = rs.getInt("CD2");
                    this.CF2 = rs.getInt("CF2");
                    this.nombre = rs.getString("nombre");
                    this.duracion = rs.getInt("duracion");

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(SesionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void persist() {
        persist = true;
    }

    public void detach() {
        persist = false;
    }

    @Override
    public void setCD(int CD) {
        super.setCD(CD);
        if (persist) {
            save();
        }
    }

    @Override
    public void setCF(int CF) {
        super.setCF(CF);
        if (persist) {
            save();
        }
    }

    @Override
    public void setCD2(int CD2) {
        super.setCD2(CD2);
        if (persist) {
            save();
        }
    }

    @Override
    public void setCF2(int CF2) {
        super.setCF2(CF2);
        if (persist) {
            save();
        }
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
        if (persist) {
            save();
        }
    }

    @Override
    public void setDuracion(int duracion) {
        super.setDuracion(duracion);
        if (persist) {
            save();
        }
    }

    public int save() {
        int resultado = -1;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.CS > 0) {
                // ACTUALIZAR
                PreparedStatement ps = csql.prepareStatement(actualizar);
                ps.setInt(1, CD);
                ps.setInt(2, CF);
                ps.setInt(3, CD2);
                ps.setInt(4, CF2);
                ps.setString(5, nombre);
                ps.setInt(6, duracion);
                ps.setInt(7, CS);
                resultado = ps.executeUpdate();

            } else {
                // INSERTAR
                PreparedStatement ps = csql.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, CD);
                ps.setInt(2, CF);
                ps.setInt(3, CD2);
                ps.setInt(4, CF2);
                ps.setString(5, nombre);
                ps.setInt(6, duracion);
                resultado = ps.executeUpdate();
                try ( ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        resultado = generatedKeys.getInt(1);
                    }
                }
                this.CS = resultado;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SesionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    // Sin parametros de busqueda
    public static List<Sesion> selectAll() {
        return selectAll("");
    }

    // Busqueda por patron
    public static List<Sesion> selectAll(String patron) {
        List<Sesion> resultado = new ArrayList<>();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM sesion";

            if (patron.length() > 0) {
                q += "WHERE nombre LIKE ?";
            }
            PreparedStatement ps = conn.prepareStatement(q);

            if (patron.length() > 0) {
                ps.setString(1, patron + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Sesion j = new Sesion();
                    j.setCS(rs.getInt("CS"));
                    j.setCD(rs.getInt("CD"));
                    j.setCF(rs.getInt("CF"));
                    j.setCD2(rs.getInt("CD2"));
                    j.setCF2(rs.getInt("CF2"));
                    j.setNombre(rs.getString("nombre"));
                    j.setDuracion(rs.getInt("duracion"));
                    resultado.add(j);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(SesionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

      public void remove(Sesion sesion) {
        PreparedStatement ps = null;
        
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(borrar);
            
            ps.setInt(1, sesion.getCS());
           

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se Ha borrado correctamente");

            }
        } catch (SQLException ex) {
            Logger.getLogger(SesionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SesionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
