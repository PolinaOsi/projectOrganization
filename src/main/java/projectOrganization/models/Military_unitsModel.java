package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Military_units;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Military_unitsModel {
    private Integer id_unit;
    private String name_unit;
    private Integer id_association;
    private List<DislocationsModel> id_dislocation;

    public static Military_unitsModel toModel(Military_units unit) {
        Military_unitsModel model = new Military_unitsModel();

        model.setId_dislocation(new ArrayList<>());
        model.setId_unit(unit.getId_unit());
        model.setName_unit(unit.getName_unit());
        model.setId_association(unit.getAssociations().getId_association());
        for (var p : unit.getDislocations()) {
            model.getId_dislocation().add(DislocationsModel.toModel(p));
        }

        return model;
    }
}
