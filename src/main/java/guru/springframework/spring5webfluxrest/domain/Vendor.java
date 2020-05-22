package guru.springframework.spring5webfluxrest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by morgan on 20.05.2020
 */

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Id
    private String id;

    private String firstName;
    private String lastName;
}