package app.assessing.skills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillTypeRepository {
  private final JdbcTemplate jdbcTemplate;

  public SkillTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<SkillType> getAllSkillTypes() {
    String sql = "SELECT * FROM skill_types";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SkillType.class));
  }

  @Override
  public void insertQuantityMeasurement(SkillSettingsDTO quantityMeasurement) {
    String sql = "INSERT INTO quantity_measurement (value) VALUES (?)";
    jdbcTemplate.update(sql, quantityMeasurement.getValue());
  }

  @Override
  public void insertQualityMeasurement(SkillSettingsDTO qualityMeasurement) {
    String sql = "INSERT INTO quality_measurement (name) VALUES (?)";
    jdbcTemplate.update(sql, quantityMeasurement.getValue());
  }
}
