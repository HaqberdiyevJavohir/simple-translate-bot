package model.TranslateModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Def {
    private String text;
    private String pos;
    private String ts;
    private Tr[] tr;
}
