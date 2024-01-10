package app.assessing.skills;

import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class Position {
  @Id
  private Long id;
  private String name;
}
