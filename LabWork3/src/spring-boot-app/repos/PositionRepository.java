package app.assessing.skills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionRepository {
  @Autowired
  private final JdbcTemplate jdbcTemplate;

  public PositionRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Position> getAllPositions() {
    String sql = "SELECT * FROM positions";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Position.class));
  }
}
