package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Dislocations;

@Data
@NoArgsConstructor
public class DislocationsModel {
    private Integer id_dislocation;
    private String city;

    public static DislocationsModel toModel(Dislocations dislocation) {
        DislocationsModel model = new DislocationsModel();
        model.setId_dislocation(dislocation.getId_dislocation());
        model.setCity(dislocation.getCity());

        return model;
    }
}
