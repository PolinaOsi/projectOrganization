package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Associations;

@Data
@NoArgsConstructor
public class AssociationsModel {
    private Integer id_association;
    private String type_association;
    private Integer num_association;
    private String name_association;

    public static AssociationsModel toModel(Associations association) {
        AssociationsModel model = new AssociationsModel();
        model.setId_association(association.getId_association());
        model.setType_association(association.getType_association());
        model.setNum_association(association.getNum_association());
        model.setName_association(association.getName_association());
        return model;
    }
}
