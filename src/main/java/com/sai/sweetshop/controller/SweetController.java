package com.sai.sweetshop.controller;

import com.sai.sweetshop.dto.QuantityRequest;
import com.sai.sweetshop.model.Sweet;
import com.sai.sweetshop.service.SweetService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @GetMapping
    public List<Sweet> getAll() {
        return sweetService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Sweet add(@Valid @RequestBody Sweet sweet) {
        return sweetService.save(sweet);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Sweet update(@PathVariable Long id, @Valid @RequestBody Sweet sweet) {
        return sweetService.update(id, sweet);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        sweetService.delete(id);
        return "Sweet deleted successfully";
    }

    @PostMapping("/{id}/purchase")
    public Sweet purchase(
            @PathVariable Long id,
            @Valid @RequestBody QuantityRequest request
    ) {
        return sweetService.purchase(id, request.getQuantity());
    }

    @PostMapping("/{id}/restock")
    @PreAuthorize("hasRole('ADMIN')")
    public Sweet restock(
            @PathVariable Long id,
            @Valid @RequestBody QuantityRequest request
    ) {
        return sweetService.restock(id, request.getQuantity());
    }

    @GetMapping("/search")
    public List<Sweet> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return sweetService.search(id, name, category, minPrice, maxPrice, quantity, sortBy);
    }
}
