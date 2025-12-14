package com.sai.sweetshop.service;

import com.sai.sweetshop.model.Sweet;
import com.sai.sweetshop.repository.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public List<Sweet> getAll() {
        return sweetRepository.findAll();
    }

    public Sweet save(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public Sweet update(Long id, Sweet updatedSweet) {
        Sweet existing = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        existing.setName(updatedSweet.getName());
        existing.setCategory(updatedSweet.getCategory());
        existing.setPrice(updatedSweet.getPrice());
        existing.setQuantity(updatedSweet.getQuantity());

        return sweetRepository.save(existing);
    }

    // ========================
// DELETE
// ========================
    public void delete(Long id) {
        if (!sweetRepository.existsById(id)) {
            throw new RuntimeException("Sweet not found"); // ✅ REQUIRED
        }
        sweetRepository.deleteById(id);
    }


    public Sweet purchase(Long id, int qty) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        if (sweet.getQuantity() < qty) {
            throw new RuntimeException("Not enough stock");
        }

        sweet.setQuantity(sweet.getQuantity() - qty);
        return sweetRepository.save(sweet);
    }

    public Sweet restock(Long id, int qty) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        sweet.setQuantity(sweet.getQuantity() + qty);
        return sweetRepository.save(sweet);
    }

    // 🔥 FINAL SEARCH + SORT
    public List<Sweet> search(
            Long id,
            String name,
            String category,
            Double minPrice,
            Double maxPrice,
            Integer quantity,
            String sortBy
    ) {
        List<Sweet> sweets = sweetRepository.findAll();

        if (id != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getId().equals(id))
                    .toList();
        }

        if (name != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (category != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        if (minPrice != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getPrice() >= minPrice)
                    .toList();
        }

        if (maxPrice != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getPrice() <= maxPrice)
                    .toList();
        }

        if (quantity != null) {
            sweets = sweets.stream()
                    .filter(s -> s.getQuantity() == quantity)
                    .toList();
        }

        // ✅ IMPORTANT FIX
        if (sortBy == null) {
            sortBy = "id";
        }

        return switch (sortBy) {
            case "name" -> sweets.stream()
                    .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                    .toList();
            case "price" -> sweets.stream()
                    .sorted((a, b) -> Double.compare(a.getPrice(), b.getPrice()))
                    .toList();
            case "quantity" -> sweets.stream()
                    .sorted((a, b) -> Integer.compare(a.getQuantity(), b.getQuantity()))
                    .toList();
            default -> sweets.stream()
                    .sorted((a, b) -> Long.compare(a.getId(), b.getId()))
                    .toList();
        };
    }

}
