package guru.springframework.spring5webfluxrest.conrollers;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Created by morgan on 22.05.2020
 */

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("")
    Flux<Vendor> list() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Vendor> getById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> create(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepository.saveAll(vendorStream).then();
    }

    @PutMapping("/{id}")
    Mono<Vendor> updateById(@PathVariable String id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/{id}")
    Mono<Vendor> patchById(@PathVariable String id, @RequestBody Vendor vendor) {
        Vendor existing = vendorRepository.findById(id).block();
        if(existing == null) {
            throw new IllegalArgumentException("No vendor found by id:" + id);
        }
        boolean changed = false;
        if(!Objects.equals(existing.getFirstName(), vendor.getFirstName())) {
            existing.setFirstName(vendor.getFirstName());
            changed = true;
        }
        if(!Objects.equals(existing.getLastName(), vendor.getLastName())) {
            existing.setLastName(vendor.getLastName());
            changed = true;
        }
        if(changed) {
            return vendorRepository.save(existing);
        }

        return Mono.just(existing);
    }
}
