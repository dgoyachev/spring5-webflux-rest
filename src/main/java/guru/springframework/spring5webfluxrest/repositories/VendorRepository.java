package guru.springframework.spring5webfluxrest.repositories;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by morgan on 20.05.2020
 */

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
