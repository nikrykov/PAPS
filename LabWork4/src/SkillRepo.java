package com.app.skill.assessment;

import com.app.skill.assessment.models.QualityValuesSkill;
import com.app.skill.assessment.models.QuantityTypeMeasurementSkillSettings;
import com.app.skill.assessment.models.QuantityValuesSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<QuantityTypeMeasurementSkillSettings> getQuantityMeasurementSkills() {
        var sql = "select \n" +
                "skills.id_skill, name_skill, id_connection, connection_position_skill.id_position,\n" +
                "min_value, skills.id_skill, name_position, name_type_measurement, type_measurement_skill.id_type_measurement\n" +
                "from skills inner join connection_position_skill on\n" +
                "skills.id_skill = connection_position_skill.id_skill inner join positions on \n" +
                "connection_position_skill.id_position = positions.id_position inner join type_measurement_skill\n" +
                "on connection_position_skill.id_type_measurement = type_measurement_skill.id_type_measurement";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(QuantityTypeMeasurementSkillSettings.class));
    }

    public List<QuantityTypeMeasurementSkillSettings> getQuantityMeasurementSkillsByPosition(Long idPosition) {
        var sql = "select\n" +
                "skills.id_skill, name_skill, connection_position_skill.id_connection, connection_position_skill.id_position,\n" +
                "min_value, skills.id_skill, name_position, name_type_measurement,\n" +
                "type_measurement_skill.id_type_measurement, id_interval, min_value_interval, max_value_interval\n" +
                "from skills inner join connection_position_skill on\n" +
                "skills.id_skill = connection_position_skill.id_skill inner join positions on\n" +
                "connection_position_skill.id_position = positions.id_position inner join type_measurement_skill\n" +
                "on connection_position_skill.id_type_measurement = type_measurement_skill.id_type_measurement\n" +
                "inner join quantity_measurement_skill on\n" +
                "connection_position_skill.id_connection = quantity_measurement_skill.id_connection\n" +
                "where connection_position_skill.id_position=?";
        return jdbcTemplate.query(sql, new Object[]{idPosition},
                new BeanPropertyRowMapper<>(QuantityTypeMeasurementSkillSettings.class));
    }

    public List<QuantityTypeMeasurementSkillSettings> getQuantityMeasurementSkillsByPositionSkill(Long idPosition, Long idSkill) {
        var sql = "select\n" +
                "skills.id_skill, name_skill, connection_position_skill.id_connection, connection_position_skill.id_position,\n" +
                "min_value, skills.id_skill, name_position, name_type_measurement,\n" +
                "type_measurement_skill.id_type_measurement, id_interval, min_value_interval, max_value_interval\n" +
                "from skills inner join connection_position_skill on\n" +
                "skills.id_skill = connection_position_skill.id_skill inner join positions on\n" +
                "connection_position_skill.id_position = positions.id_position inner join type_measurement_skill\n" +
                "on connection_position_skill.id_type_measurement = type_measurement_skill.id_type_measurement\n" +
                "inner join quantity_measurement_skill on\n" +
                "connection_position_skill.id_connection = quantity_measurement_skill.id_connection\n" +
                "where connection_position_skill.id_position=? and skills.id_skill=?";
        return jdbcTemplate.query(sql, new Object[]{idPosition, idSkill},
                new BeanPropertyRowMapper<>(QuantityTypeMeasurementSkillSettings.class));
    }

    public void saveQuantityMeasurementSkill(QuantityValuesSkill quantitySkill) {
        var sql = "insert into quantity_measurement_skill (id_connection, min_value_interval, max_value_interval)" +
                " values (?, ?, ?)";
        jdbcTemplate.update(sql, quantitySkill.getIdConnection(), quantitySkill.getMinValue(), quantitySkill.getMaxValue());
    }

    public void saveQualityMeasurementSkill(QualityValuesSkill qualityValuesSkill) {
        var sql = "insert into quality_measurement_skill (id_connection, val, order_val)" +
                " values (?, ?, ?)";
        jdbcTemplate.update(sql, qualityValuesSkill.getIdConnection(), qualityValuesSkill.getValue(), qualityValuesSkill.getOrder());
    }

    public void updateQuantityMeasurementSkill(QuantityValuesSkill quantitySkill) {
        var sql = "update quantity_measurement_skill set min_value_interval = ?, max_value_interval = ?" +
                " where id_interval = ?";
        jdbcTemplate.update(sql, quantitySkill.getMinValue(), quantitySkill.getMaxValue(), quantitySkill.getIdInterval());
    }

    public void updateQualityMeasurementSkill(QualityValuesSkill qualityValuesSkill) {
        var sql = "update quality_measurement_skill set val = ?, order_val = ? " +
                "where id_value = ?";
        jdbcTemplate.update(sql, qualityValuesSkill.getValue(), qualityValuesSkill.getOrder(), qualityValuesSkill.getIdValue());
    }

    public void deleteQuantityMeasurementSkill(Long idConnection) {
        var sql = "delete from quantity_measurement_skill where id_connection = ?";
        jdbcTemplate.update(sql, idConnection);
    }

    public void deleteQualityMeasurementSkill(Long idConnection) {
        var sql = "delete from quality_measurement_skill where id_connection = ?";
        jdbcTemplate.update(sql, idConnection);
    }
}
