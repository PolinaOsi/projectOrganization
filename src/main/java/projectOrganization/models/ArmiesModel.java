package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Armies;

@Data
@NoArgsConstructor
public class ArmiesModel {
    private Integer id_army;
    private String name_army; public static ArmiesModel toModel(Armies army) {
        ArmiesModel model = new ArmiesModel();

        model.setId_army(army.getId_army());
        model.setName_army(army.getName_army());
        return model;
    }


}