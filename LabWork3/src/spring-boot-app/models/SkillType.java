package app.assessing.skills;

import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class SkillType {
  @Id
  private Long id;
  private String name;
}