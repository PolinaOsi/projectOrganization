package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Armaments;

@Data
@NoArgsConstructor
public class ArmamentsModel {
    private Integer id_armament;
    private String name_armament;
    private Integer count_armament;
    private Integer id_unit;

        public static ArmamentsModel toModel(Armaments armament) {
            ArmamentsModel model = new ArmamentsModel();

            model.setId_armament(armament.getId_armament());
            model.setName_armament(armament.getName_armament());
            model.setCount_armament(armament.getCount_armament());
            model.setId_unit(armament.getId_unit());
            return model;
        }
}


