package app.assessing.skills;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillsSettingsServiceImpl implements SkillsSettingsService {
  private final PositionRepository positionRepository;
  private final SkillTypeRepository skillTypeRepository;

  public SkillsSettingsServiceImpl(PositionRepository positionRepository, SkillTypeRepository skillTypeRepository) {
    this.positionRepository = positionRepository;
    this.skillTypeRepository = skillTypeRepository;
  }

  @Override
  public List<Position> getAllPositions() {
    return positionRepository.getAllPositions();
  }

  @Override
  public List<SkillType> getAllSkillTypes() {
    return skillTypeRepository.getAllSkillTypes();
  }

  @Override
  public void saveSkillSettings(String skill, String position, String skillType, String minValue, String maxValue, List<String> qualitativeValues) {
    // Логика сохранения настроек навыков сотрудников
  }
}
