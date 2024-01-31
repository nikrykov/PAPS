package app.assessing.skills;

import org.springframework.stereotype.Service;
import java.util.List;

public interface SkillsSettingsService {
  List<Position> getAllPositions();
  List<SkillType> getAllSkillTypes();
  void saveSkillSettings(String skill, String position, String skillType, String minValue, String maxValue, List<String> qualitativeValues);
}