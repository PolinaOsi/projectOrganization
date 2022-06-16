package projectOrganization.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import projectOrganization.entity.Ranks;

@Data
@NoArgsConstructor
public class RanksModel {
    private Integer id_rank;
    private String name_rank;
    private String category;

    public static RanksModel toModel(Ranks rank) {
        RanksModel model = new RanksModel();

        model.setId_rank(rank.getId_rank());
        model.setName_rank(rank.getName_rank());
        model.setCategory(rank.getCategory());
        return model;
    }

}
