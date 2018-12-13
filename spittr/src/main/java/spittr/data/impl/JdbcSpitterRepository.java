//package spittr.data.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//import spittr.Spitter;
//import spittr.data.SpitterRepository;
//
//
//@Repository
//public class JdbcSpitterRepository implements SpitterRepository {
//  private static final String SQL_UPDATE_SPITTER =
//      "update spitter set username = ?, password = ?, fullname = ?"
//          + "where id = ?";
//
//  private static final String SQL_SELECT_SPITTER =
//      "select id, username, fullname from spitter where id = ?";
//
//  private JdbcOperations jdbcOperations;
//
//  @Autowired
//  public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
//    this.jdbcOperations = jdbcOperations;
//  }
//
//  @Override
//  public Spitter save(Spitter spitter) {
//    jdbcOperations.update(SQL_UPDATE_SPITTER);
//     return spitter;
//  }
//
//  @Override
//  public Spitter findByUsername(String username) {
//    return null;
//  }
//
//  @Override
//  public Spitter findOne(long id) {
////    jdbcOperations.queryForObject(SQL_SELECT_SPITTER, new SpitterRowMapper(),id);
//    jdbcOperations.queryForObject(SQL_SELECT_SPITTER, (rs, rowNum) -> {
//      return new Spitter();
//    },id);
//    return null;
//  }
//
//  private static final class SpitterRowMapper implements RowMapper<Spitter>{
//
//    @Override
//    public Spitter mapRow(ResultSet resultSet, int i) throws SQLException {
//      return new Spitter();
//    }
//  }
//
//@Override
//public List<Spitter> findAll() {
//	// TODO Auto-generated method stub
//	return null;
//}
//}
