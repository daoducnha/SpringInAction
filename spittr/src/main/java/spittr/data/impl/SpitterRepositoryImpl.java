//package spittr.data.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import spittr.Spitter;
//import spittr.data.SpitterRepository;
//
////@Component
//public class SpitterRepositoryImpl implements SpitterRepository {
//
//  private static final String SQL_UPDATE_SPITTER =
//      "update spitter set username = ?, password = ?, fullname = ?"
//          + "where id = ?";
//
//  private static final String SQL_SELECT_SPITTER =
//      "select id, username, fullname from spitter where id = ?";
//
//  private DataSource dataSource;
//
//  @Autowired
//  public SpitterRepositoryImpl(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }
//
//  @Override
//  public Spitter save(Spitter spitter) {
//    Connection conn = null;
//    PreparedStatement stmt = null;
//    try {
//      conn = dataSource.getConnection();
//      stmt.setString(1, spitter.getUsername());
//      stmt.setString(2, spitter.getPassword());
//      stmt.setLong(4, spitter.getId());
//      stmt.execute();
//    } catch (SQLException ex) {
//
//    } finally {
//      try {
//        if (stmt != null) {
//          stmt.close();
//        }
//        if (conn != null) {
//          conn.close();
//        }
//      } catch (SQLException ex) {
//      }
//    }
//    return null;
//  }
//
//  @Override
//  public Spitter findByUsername(String username) {
//    return null;
////    return new Spitter("Dao","Duc Nha","ducnha","123456","example@email.com");
//  }
//
//  @Override
//  public Spitter findOne(long id) {
//    Connection conn = null;
//    PreparedStatement stmt = null;
//    ResultSet rs = null;
//    try {
//
//      conn = dataSource.getConnection();
//      stmt = conn.prepareStatement(SQL_SELECT_SPITTER);
//
//      stmt.setLong(1, id);
//      rs = stmt.executeQuery();
//
//      Spitter spitter = null;
//      if (rs.next()) {
//        spitter = new Spitter();
//        spitter.setId(rs.getLong("id"));
//        spitter.setUsername(rs.getString("username"));
//        spitter.setPassword(rs.getString("password"));
//
//      }
//      return spitter;
//    } catch (SQLException ex) {
//
//    } finally {
//      if (rs != null) {
//        try {
//          rs.close();
//        } catch (SQLException e) {
//        }
//      }
//      if (stmt != null) {
//        try {
//          stmt.close();
//        } catch (SQLException e) {
//        }
//      }
//      if (conn != null) {
//        try {
//          conn.close();
//        } catch (SQLException e) {
//        }
//      }
//    }
//    return null;
//  }
//
//@Override
//public List<Spitter> findAll() {
//	// TODO Auto-generated method stub
//	return null;
//}
//}
